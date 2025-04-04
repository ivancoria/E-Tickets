package com.ivancoria.etickets.controllers;

import com.ivancoria.etickets.dtos.CustomerDTO;
import com.ivancoria.etickets.dtos.OrganizerDTO;
import com.ivancoria.etickets.dtos.requests.LoginRequest;
import com.ivancoria.etickets.dtos.requests.ResetPasswordRequest;
import com.ivancoria.etickets.dtos.responses.ApiResponse;
import com.ivancoria.etickets.dtos.responses.AuthResponse;
import com.ivancoria.etickets.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest loginRequest){
        final AuthResponse authResponse = authService.login(loginRequest);
        return ResponseEntity.ok(ApiResponse
                .success(HttpStatus.OK.value(), "Login Exitoso", authResponse));
    }

    @PostMapping("/register/customer")
    public ResponseEntity<ApiResponse<AuthResponse>> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        final AuthResponse authResponse = authService.registerCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse
                .success(HttpStatus.CREATED.value(), "Registro Exitoso", authResponse));
    }

    @PostMapping("/register/organizer")
    public ResponseEntity<ApiResponse<AuthResponse>> registerOrganizer(@Valid @RequestBody OrganizerDTO organizerDTO){
        final AuthResponse authResponse = authService.registerOrganizer(organizerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse
                .success(HttpStatus.CREATED.value(), "Registro Exitoso", authResponse));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<AuthResponse>> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        final AuthResponse authResponse = authService.resetPassword(request);
        return ResponseEntity.ok(ApiResponse
                .success(HttpStatus.OK.value(), "Contraseña restablecida", authResponse));
    }



}
