package com.ivancoria.etickets.services.validations;

import com.ivancoria.etickets.entities.UserEntity;
import com.ivancoria.etickets.exceptions.customExceptions.PasswordMismatchException;
import com.ivancoria.etickets.exceptions.customExceptions.ResourceNotFoundException;
import com.ivancoria.etickets.exceptions.customExceptions.UserAlreadyExistsException;
import com.ivancoria.etickets.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

    private final UserRepository userRepository;

    public UserValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity ValidateUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("El Usuario no existe"));
    }

    public void ValidateExistEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("El Email ya esta en uso cumpa");
        }
    }

    public void ValidatePasswordMatch(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new PasswordMismatchException("Las contraseñas no coinciden");
        }
    }
}
