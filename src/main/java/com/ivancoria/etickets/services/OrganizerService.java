package com.ivancoria.etickets.services;

import com.ivancoria.etickets.dtos.organizer.OrganizerProfileDTO;
import com.ivancoria.etickets.entities.OrganizerEntity;
import com.ivancoria.etickets.entities.UserEntity;
import com.ivancoria.etickets.exceptions.customExceptions.ResourceNotFoundException;
import com.ivancoria.etickets.mappers.OrganizerMapper;
import com.ivancoria.etickets.repositories.OrganizerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService {

    private final OrganizerRepository organizerRepository;
    private final OrganizerMapper organizerMapper;

    public OrganizerService(OrganizerRepository organizerRepository, OrganizerMapper organizerMapper) {
        this.organizerRepository = organizerRepository;
        this.organizerMapper = organizerMapper;
    }

    public OrganizerProfileDTO getMyProfile(Authentication authentication) {
        UserEntity organizer = (UserEntity) authentication.getPrincipal();
        OrganizerEntity organizerEntity = organizerRepository.findById(organizer.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el Usuario"));
        return organizerMapper.entityToProfileDTO(organizerEntity);
    }
}
