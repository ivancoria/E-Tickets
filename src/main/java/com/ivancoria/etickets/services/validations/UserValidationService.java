package com.ivancoria.etickets.services.validations;

import com.ivancoria.etickets.entities.CustomerEntity;
import com.ivancoria.etickets.entities.UserEntity;
import com.ivancoria.etickets.exceptions.customExceptions.NoDataChangedException;
import com.ivancoria.etickets.exceptions.customExceptions.PasswordMismatchException;
import com.ivancoria.etickets.exceptions.customExceptions.ResourceNotFoundException;
import com.ivancoria.etickets.exceptions.customExceptions.UserAlreadyExistsException;
import com.ivancoria.etickets.repositories.CustomerRepository;
import com.ivancoria.etickets.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    public UserValidationService(UserRepository userRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    public UserEntity validateUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("El Usuario no existe"));
    }

    public void validateExistEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("El Email ya esta en uso");
        }
    }

    public CustomerEntity validateCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Usuario no existe"));
    }

    public void validatePasswordMatch(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new PasswordMismatchException("Las contraseñas no coinciden");
        }
    }

    public void validateEmailHasChanged(String currentEmail, String newEmail) {
        if (currentEmail.equals(newEmail)) {
            throw new NoDataChangedException("El Email es el mismo");
        }
    }
}
