package com.ivancoria.etickets.controllers;

import com.ivancoria.etickets.dtos.event.EventDTO;
import com.ivancoria.etickets.dtos.organizer.OrganizerProfileDTO;
import com.ivancoria.etickets.dtos.organizer.OrganizerUpdateDTO;
import com.ivancoria.etickets.dtos.responses.ApiResponse;
import com.ivancoria.etickets.services.OrganizerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organizers")
public class OrganizerController {

    private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @GetMapping("/me/profile")
    public ResponseEntity<ApiResponse<OrganizerProfileDTO>> getMyProfile(Authentication authentication) {
        final OrganizerProfileDTO organizerProfile = organizerService.getMyProfile(authentication);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Datos del usuario", organizerProfile));
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @PatchMapping("/me/update")
    public ResponseEntity<ApiResponse<OrganizerProfileDTO>> updateOrganizer(
            @RequestBody OrganizerUpdateDTO organizerUpdateDTO, Authentication authentication) {
        final OrganizerProfileDTO organizerProfile = organizerService.updateOrganizer(organizerUpdateDTO, authentication);
        return ResponseEntity.ok(ApiResponse
                .success(HttpStatus.OK.value(), "Datos del usuario actualizados", organizerProfile));
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @GetMapping("/me/events")
    public ResponseEntity<ApiResponse<List<EventDTO>>> myEvents(Authentication authentication) {
        List<EventDTO> myEvents = organizerService.myEvents(authentication);
        return ResponseEntity.ok(ApiResponse
                .success(HttpStatus.OK.value(), "Lista de eventos", myEvents));
    }
}
