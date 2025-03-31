package com.ivancoria.etickets.services;

import com.ivancoria.etickets.dtos.EventDTO;
import com.ivancoria.etickets.entities.EventEntity;
import com.ivancoria.etickets.entities.OrganizerEntity;
import com.ivancoria.etickets.entities.UserEntity;
import com.ivancoria.etickets.exceptions.customExceptions.ResourceNotFoundException;
import com.ivancoria.etickets.mappers.EventMapper;
import com.ivancoria.etickets.repositories.EventRepository;
import com.ivancoria.etickets.repositories.OrganizerRepository;
import com.ivancoria.etickets.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final OrganizerRepository organizerRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, UserRepository userRepository,
                        OrganizerRepository organizerRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.organizerRepository = organizerRepository;
        this.eventMapper = eventMapper;
    }

    public EventDTO createEvent(EventDTO eventDTO, Authentication authentication) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        OrganizerEntity organizer = organizerRepository.findById(userEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Organizador no encontrado"));
        EventEntity eventEntity = eventMapper.dtoToEntity(eventDTO);
        eventEntity.setOrganizer(organizer);
        eventRepository.save(eventEntity);
        return eventMapper.entityToDTO(eventEntity);
    }

    public EventDTO updateEvent(Long eventId, EventDTO eventDTO, Authentication authentication) {
        EventEntity eventEntity = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        if (!eventEntity.getOrganizer().getUsername().equals(username)) {
            throw new ResourceNotFoundException("No eres el organizador de este evento");
        }
        eventMapper.updateEntityFromDTO(eventDTO, eventEntity);
        EventEntity updatedEvent = eventRepository.save(eventEntity);
        return eventMapper.entityToDTO(updatedEvent);
    }

    public List<EventDTO> myEvents(Authentication authentication) {
        String organizerEmail = authentication.getName();
        Long organizerId = userRepository.findIdByEmail(organizerEmail);
        List<EventEntity> events = eventRepository.findByOrganizerId(organizerId);
        return eventMapper.entitiesToDTOs(events);
    }
}
