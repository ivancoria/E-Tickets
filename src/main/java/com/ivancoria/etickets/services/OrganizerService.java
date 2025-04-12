package com.ivancoria.etickets.services;

import com.ivancoria.etickets.dtos.customer.CustomerUpdateDTO;
import com.ivancoria.etickets.dtos.organizer.OrganizerProfileDTO;
import com.ivancoria.etickets.dtos.organizer.OrganizerUpdateDTO;
import com.ivancoria.etickets.entities.CustomerEntity;
import com.ivancoria.etickets.entities.OrganizerEntity;
import com.ivancoria.etickets.entities.UserEntity;
import com.ivancoria.etickets.exceptions.customExceptions.NoDataChangedException;
import com.ivancoria.etickets.exceptions.customExceptions.ResourceNotFoundException;
import com.ivancoria.etickets.exceptions.customExceptions.UserAlreadyExistsException;
import com.ivancoria.etickets.mappers.OrganizerMapper;
import com.ivancoria.etickets.repositories.OrganizerRepository;
import com.ivancoria.etickets.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService {

    private final UserRepository userRepository;
    private final OrganizerRepository organizerRepository;
    private final OrganizerMapper organizerMapper;

    public OrganizerService(UserRepository userRepository, OrganizerRepository organizerRepository,
                            OrganizerMapper organizerMapper) {
        this.userRepository = userRepository;
        this.organizerRepository = organizerRepository;
        this.organizerMapper = organizerMapper;
    }

    public OrganizerProfileDTO getMyProfile(Authentication authentication) {
        UserEntity organizer = (UserEntity) authentication.getPrincipal();
        OrganizerEntity organizerEntity = organizerRepository.findById(organizer.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el Usuario"));
        return organizerMapper.entityToProfileDTO(organizerEntity);
    }

    public OrganizerProfileDTO updateOrganizer(OrganizerUpdateDTO organizerUpdateDTO, Authentication authentication) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        OrganizerEntity organizerEntity = organizerRepository.findById(userEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        if (organizerEntity.getEmail().equals(organizerUpdateDTO.getEmail())) {
            throw new NoDataChangedException("El Email es el mismo");
        }
        if (userRepository.existsByEmail(organizerUpdateDTO.getEmail())) {
            throw new UserAlreadyExistsException("El Email ya esta en uso");
        }
        organizerMapper.updateDTOToEntity(organizerUpdateDTO, organizerEntity);
        organizerRepository.save(organizerEntity);
        return organizerMapper.entityToProfileDTO(organizerEntity);
    }
}
