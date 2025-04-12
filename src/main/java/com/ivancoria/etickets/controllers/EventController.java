package com.ivancoria.etickets.controllers;

import com.ivancoria.etickets.dtos.event.EventDTO;
import com.ivancoria.etickets.dtos.responses.ApiResponse;
import com.ivancoria.etickets.services.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    public ResponseEntity<ApiResponse<EventDTO>> createEvent(@Valid @RequestBody EventDTO eventDTO, Authentication authentication){
        final EventDTO event = eventService.createEvent(eventDTO, authentication);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(ApiResponse
                .success(HttpStatus.CREATED.value(), "Evento creado con exito", event));
    }

    @PutMapping("/{eventId}")
    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    public ResponseEntity<ApiResponse<EventDTO>> updateEvent(@PathVariable Long eventId,
                              @Valid @RequestBody EventDTO eventDTO,
                              Authentication authentication) {
        final EventDTO updateEvent = eventService.updateEvent(eventId, eventDTO, authentication);
        return ResponseEntity.ok(ApiResponse
                .success(HttpStatus.OK.value(), "Evento actualizado", updateEvent));
    }
}
