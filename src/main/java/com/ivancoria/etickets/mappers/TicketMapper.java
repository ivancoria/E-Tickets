package com.ivancoria.etickets.mappers;

import com.ivancoria.etickets.dtos.ticket.TicketDTO;
import com.ivancoria.etickets.entities.TicketEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {


    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "event.id", target = "eventId")
    TicketDTO entityToDTO (TicketEntity ticketEntity);

    TicketEntity dtoToEntity (TicketDTO ticketDTO);

    List<TicketDTO> entitiesToDTOs(List<TicketEntity> ticketEntities);
}
