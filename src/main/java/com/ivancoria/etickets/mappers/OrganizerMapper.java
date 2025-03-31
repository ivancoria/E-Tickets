package com.ivancoria.etickets.mappers;

import com.ivancoria.etickets.dtos.OrganizerDTO;
import com.ivancoria.etickets.entities.OrganizerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/* "unmappedTargetPolicy" para que ingnore "confirmPassword" */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrganizerMapper {

    OrganizerEntity dtoToEntity (OrganizerDTO organizerDTO);

    OrganizerDTO entityToDTO (OrganizerEntity organizerEntity);
}
