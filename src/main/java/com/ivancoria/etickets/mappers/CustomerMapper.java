package com.ivancoria.etickets.mappers;

import com.ivancoria.etickets.dtos.CustomerDTO;
import com.ivancoria.etickets.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/* "unmappedTargetPolicy" para que ingnore "confirmPassword" */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    CustomerEntity dtoToEntity(CustomerDTO customerDTO);

    CustomerDTO entityToDTO(CustomerEntity customerEntity);

}
