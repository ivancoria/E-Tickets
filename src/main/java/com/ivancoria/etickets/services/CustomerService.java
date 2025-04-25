package com.ivancoria.etickets.services;

import com.ivancoria.etickets.dtos.customer.CustomerProfileDTO;
import com.ivancoria.etickets.dtos.customer.CustomerUpdateDTO;
import com.ivancoria.etickets.dtos.ticket.TicketDTO;
import com.ivancoria.etickets.entities.CustomerEntity;
import com.ivancoria.etickets.entities.TicketEntity;
import com.ivancoria.etickets.entities.UserEntity;
import com.ivancoria.etickets.exceptions.customExceptions.ResourceNotFoundException;
import com.ivancoria.etickets.mappers.CustomerMapper;
import com.ivancoria.etickets.mappers.TicketMapper;
import com.ivancoria.etickets.repositories.CustomerRepository;
import com.ivancoria.etickets.repositories.TicketRepository;
import com.ivancoria.etickets.repositories.UserRepository;
import com.ivancoria.etickets.services.validations.UserValidationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final UserRepository userRepository;
    private final UserValidationService userValidationService;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    public CustomerService(UserRepository userRepository, CustomerRepository customerRepository,
                           CustomerMapper customerMapper, TicketRepository ticketRepository,
                           TicketMapper ticketMapper, UserValidationService userValidationService) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.userValidationService = userValidationService;
    }

    public CustomerProfileDTO getMyProfile(Authentication authentication) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        CustomerEntity customerEntity = (CustomerEntity) userValidationService.validateUser(userEntity.getEmail());
        return customerMapper.entityToProfileDTO(customerEntity);
    }

    public CustomerProfileDTO updateCustomer(CustomerUpdateDTO customerUpdateDTO, Authentication authentication) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        CustomerEntity customerEntity = userValidationService.validateCustomerById(userEntity.getId());
        userValidationService.validateEmailHasChanged(customerEntity.getEmail(), customerUpdateDTO.getEmail());
        userValidationService.validateExistEmail(customerUpdateDTO.getEmail());
        customerMapper.updateDTOToEntity(customerUpdateDTO, customerEntity);
        customerRepository.save(customerEntity);
        return customerMapper.entityToProfileDTO(customerEntity);
    }

    public List<TicketDTO> myTickets(Authentication authentication) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        List<TicketEntity> tickets = ticketRepository.findByCustomerId(userEntity.getId());
        if (tickets.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron tickets para este usuario");
        }
        return ticketMapper.entitiesToDTOs(tickets);
    }

}
