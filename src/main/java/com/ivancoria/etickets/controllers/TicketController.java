package com.ivancoria.etickets.controllers;

import com.ivancoria.etickets.dtos.ticket.TicketDTO;

import com.ivancoria.etickets.dtos.responses.ApiResponse;
import com.ivancoria.etickets.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/events/{eventId}")
    public ResponseEntity<ApiResponse<TicketDTO>> buyTicket (@PathVariable Long eventId, Authentication authentication) throws AccessDeniedException {
        final TicketDTO ticket = ticketService.createTicket(eventId, authentication);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(ApiResponse
                .success(HttpStatus.CREATED.value(), "Ticket comprado con exito", ticket));
    }
}
