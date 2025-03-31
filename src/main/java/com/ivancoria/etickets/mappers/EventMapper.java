package com.ivancoria.etickets.mappers;

import com.ivancoria.etickets.dtos.EventDTO;
import com.ivancoria.etickets.entities.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventEntity dtoToEntity(EventDTO eventDTO);

    @Mapping(source = "organizer.id", target = "organizerId")
    EventDTO entityToDTO(EventEntity eventEntity);

    /* Importante para que el organizador_id y el id del evento no puedan ser modificados */
    @Mapping(target = "organizer", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(EventDTO eventDTO, @MappingTarget EventEntity eventEntity);

    List<EventDTO> entitiesToDTOs(List<EventEntity> eventEntities);
}
