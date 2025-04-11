package com.ivancoria.etickets.services;

import com.ivancoria.etickets.dtos.customer.CustomerProfileDTO;
import com.ivancoria.etickets.dtos.customer.CustomerUpdateDTO;
import com.ivancoria.etickets.entities.CustomerEntity;
import com.ivancoria.etickets.entities.UserEntity;
import com.ivancoria.etickets.exceptions.customExceptions.NoDataChangedException;
import com.ivancoria.etickets.exceptions.customExceptions.ResourceNotFoundException;
import com.ivancoria.etickets.exceptions.customExceptions.UserAlreadyExistsException;
import com.ivancoria.etickets.mappers.CustomerMapper;
import com.ivancoria.etickets.repositories.CustomerRepository;
import com.ivancoria.etickets.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(UserRepository userRepository, CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerProfileDTO getMyProfile(Authentication authentication) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        CustomerEntity customerEntity = customerRepository.findById(userEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return customerMapper.entityToProfileDTO(customerEntity);
    }

    public CustomerProfileDTO updateCustomer(CustomerUpdateDTO customerUpdateDTO, Authentication authentication) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        CustomerEntity customerEntity = customerRepository.findById(userEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        if (customerEntity.getEmail().equals(customerUpdateDTO.getEmail())) {
            throw new NoDataChangedException("El Email es el mismo");
        }
        if (userRepository.existsByEmail(customerUpdateDTO.getEmail())) {
            throw new UserAlreadyExistsException("El Email ya esta en uso");
        }
        customerMapper.updateDTOToEntity(customerUpdateDTO, customerEntity);
        customerRepository.save(customerEntity);
        return customerMapper.entityToProfileDTO(customerEntity);
    }

}
