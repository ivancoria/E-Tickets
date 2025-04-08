package com.ivancoria.etickets.services;

import com.ivancoria.etickets.dtos.customer.CustomerCreateDTO;
import com.ivancoria.etickets.dtos.organizer.OrganizerCreateDTO;
import com.ivancoria.etickets.dtos.requests.LoginRequest;
import com.ivancoria.etickets.dtos.requests.ResetPasswordRequest;
import com.ivancoria.etickets.dtos.responses.AuthResponse;
import com.ivancoria.etickets.entities.CustomerEntity;
import com.ivancoria.etickets.entities.OrganizerEntity;
import com.ivancoria.etickets.entities.UserEntity;
import com.ivancoria.etickets.entities.enums.UserRole;
import com.ivancoria.etickets.exceptions.customExceptions.PasswordMismatchException;
import com.ivancoria.etickets.exceptions.customExceptions.ResourceNotFoundException;
import com.ivancoria.etickets.exceptions.customExceptions.UserAlreadyExistsException;
import com.ivancoria.etickets.mappers.CustomerMapper;
import com.ivancoria.etickets.mappers.OrganizerMapper;
import com.ivancoria.etickets.repositories.CustomerRepository;
import com.ivancoria.etickets.repositories.OrganizerRepository;
import com.ivancoria.etickets.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final OrganizerRepository organizerRepository;
    private final CustomerMapper customerMapper;
    private final OrganizerMapper organizerMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, CustomerRepository customerRepository,
                       OrganizerRepository organizerRepository, CustomerMapper customerMapper,
                       OrganizerMapper organizerMapper, JwtService jwtService,
                       PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.organizerRepository = organizerRepository;
        this.customerMapper = customerMapper;
        this.organizerMapper = organizerMapper;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()));
        UserEntity userEntity = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Credenciales incorrectas"));
        var jwtToken = jwtService.generateToken(userEntity);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse registerCustomer(CustomerCreateDTO customerCreateDTO) {
        if (userRepository.existsByEmail(customerCreateDTO.getEmail())) {
            throw new UserAlreadyExistsException("El Email ya esta en uso");
        }
        if (!customerCreateDTO.getPassword().equals(customerCreateDTO.getConfirmPassword())) {
            throw new PasswordMismatchException("Las contraseñas no coinciden");
        }
        CustomerEntity customerEntity = customerMapper.createDTOToEntity(customerCreateDTO);
        customerEntity.setRole(UserRole.CUSTOMER);
        customerEntity.setPassword(passwordEncoder.encode(customerEntity.getPassword()));
        customerRepository.save(customerEntity);
        return AuthResponse.builder()
                .accessToken(jwtService.generateToken(customerEntity))
                .build();
    }

    public AuthResponse registerOrganizer(OrganizerCreateDTO organizerCreateDTO) {
        if (userRepository.existsByEmail(organizerCreateDTO.getEmail())) {
            throw new UserAlreadyExistsException("El Email ya esta en uso");
        }
        if (!organizerCreateDTO.getPassword().equals(organizerCreateDTO.getConfirmPassword())) {
            throw new PasswordMismatchException("Las contraseñas no coinciden");
        }
        OrganizerEntity organizerEntity = organizerMapper.createDTOToEntity(organizerCreateDTO);
        organizerEntity.setRole(UserRole.ORGANIZER);
        organizerEntity.setPassword(passwordEncoder.encode(organizerEntity.getPassword()));
        organizerRepository.save(organizerEntity);
        return AuthResponse.builder()
                .accessToken(jwtService.generateToken(organizerEntity))
                .build();
    }

    public void resetPassword(ResetPasswordRequest request) {
        UserEntity userEntity = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("El Usuario no existe"));
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("Las contraseñas no coinciden");
        }
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(userEntity);
    }
}
