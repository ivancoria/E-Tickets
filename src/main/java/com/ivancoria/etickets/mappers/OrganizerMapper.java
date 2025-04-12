package com.ivancoria.etickets.mappers;

import com.ivancoria.etickets.dtos.customer.CustomerUpdateDTO;
import com.ivancoria.etickets.dtos.organizer.OrganizerCreateDTO;
import com.ivancoria.etickets.dtos.organizer.OrganizerDTO;
import com.ivancoria.etickets.dtos.organizer.OrganizerProfileDTO;
import com.ivancoria.etickets.dtos.organizer.OrganizerUpdateDTO;
import com.ivancoria.etickets.entities.CustomerEntity;
import com.ivancoria.etickets.entities.OrganizerEntity;
import org.mapstruct.*;

/* "unmappedTargetPolicy" para que ingnore atributos no requeridos por los DTOs */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrganizerMapper {

    OrganizerEntity dtoToEntity(OrganizerDTO organizerDTO);

    OrganizerDTO entityToDTO(OrganizerEntity organizerEntity);

    OrganizerProfileDTO entityToProfileDTO(OrganizerEntity organizerEntity);

    OrganizerEntity createDTOToEntity(OrganizerCreateDTO organizerCreateDTO);

    /* El mapper ignora los campos en NULL */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDTOToEntity(OrganizerUpdateDTO dto, @MappingTarget OrganizerEntity organizerEntity);
}
