package com.ivancoria.etickets.mappers;

import com.ivancoria.etickets.dtos.customer.CustomerCreateDTO;
import com.ivancoria.etickets.dtos.customer.CustomerDTO;
import com.ivancoria.etickets.dtos.customer.CustomerProfileDTO;
import com.ivancoria.etickets.dtos.customer.CustomerUpdateDTO;
import com.ivancoria.etickets.entities.CustomerEntity;
import org.mapstruct.*;

/* "unmappedTargetPolicy" para que ingnore "confirmPassword" */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    CustomerEntity dtoToEntity(CustomerDTO customerDTO);

    CustomerDTO entityToDTO(CustomerEntity customerEntity);

    CustomerEntity createDTOToEntity(CustomerCreateDTO customerCreateDTO);

    CustomerProfileDTO entityToProfileDTO(CustomerEntity customerEntity);

    /* El mapper ignora los campos en NULL */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDTOToEntity(CustomerUpdateDTO dto, @MappingTarget CustomerEntity customerEntity);
}
