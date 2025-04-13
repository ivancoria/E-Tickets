package com.ivancoria.etickets.services;

import com.ivancoria.etickets.dtos.ticket.TicketDTO;
import com.ivancoria.etickets.entities.CustomerEntity;
import com.ivancoria.etickets.entities.EventEntity;
import com.ivancoria.etickets.entities.TicketEntity;
import com.ivancoria.etickets.entities.UserEntity;
import com.ivancoria.etickets.exceptions.customExceptions.ResourceNotFoundException;
import com.ivancoria.etickets.mappers.TicketMapper;
import com.ivancoria.etickets.repositories.CustomerRepository;
import com.ivancoria.etickets.repositories.EventRepository;
import com.ivancoria.etickets.repositories.TicketRepository;
import com.ivancoria.etickets.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final EventRepository eventRepository;

    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper,
                         UserRepository userRepository, CustomerRepository customerRepository,
                         EventRepository eventRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.eventRepository = eventRepository;
    }

    public TicketDTO createTicket(Long eventId, Authentication authentication) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        EventEntity event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el Evento"));
        CustomerEntity customer = customerRepository.findById(userEntity.getId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el Usuario"));
        TicketEntity ticket = new TicketEntity();
        ticket.setCustomer(customer);
        ticket.setEvent(event);

        ticketRepository.save(ticket);

        return ticketMapper.entityToDTO(ticket);
    }
}
