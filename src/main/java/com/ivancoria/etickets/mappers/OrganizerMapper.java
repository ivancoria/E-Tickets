package com.ivancoria.etickets.mappers;

import com.ivancoria.etickets.dtos.organizer.OrganizerDTO;
import com.ivancoria.etickets.dtos.organizer.OrganizerProfileDTO;
import com.ivancoria.etickets.entities.OrganizerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/* "unmappedTargetPolicy" para que ingnore atributos no requeridos por los DTOs */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrganizerMapper {

    OrganizerEntity dtoToEntity(OrganizerDTO organizerDTO);

    OrganizerDTO entityToDTO(OrganizerEntity organizerEntity);

    OrganizerProfileDTO entityToProfileDTO(OrganizerEntity organizerEntity);
}
