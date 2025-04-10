package com.ivancoria.etickets.services;

import com.ivancoria.etickets.dtos.customer.CustomerProfileDTO;
import com.ivancoria.etickets.entities.CustomerEntity;
import com.ivancoria.etickets.entities.UserEntity;
import com.ivancoria.etickets.exceptions.customExceptions.ResourceNotFoundException;
import com.ivancoria.etickets.mappers.CustomerMapper;
import com.ivancoria.etickets.repositories.CustomerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerProfileDTO getMyProfile(Authentication authentication) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        CustomerEntity customerEntity = customerRepository.findById(userEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return customerMapper.entityToProfileDTO(customerEntity);
    }
}
