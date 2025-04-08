package com.ivancoria.etickets.controllers;

import com.ivancoria.etickets.dtos.organizer.OrganizerProfileDTO;
import com.ivancoria.etickets.dtos.responses.ApiResponse;
import com.ivancoria.etickets.services.OrganizerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizers")
public class OrganizerController {

    private final OrganizerService organizerService;


    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @GetMapping("/my-profile")
    public ResponseEntity<ApiResponse<OrganizerProfileDTO>> getMyProfile(Authentication authentication) {
        final OrganizerProfileDTO organizerProfile = organizerService.getMyProfile(authentication);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Datos del usuario", organizerProfile));
    }


}
