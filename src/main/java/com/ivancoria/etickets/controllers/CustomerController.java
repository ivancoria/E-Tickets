package com.ivancoria.etickets.controllers;

import com.ivancoria.etickets.dtos.customer.CustomerProfileDTO;
import com.ivancoria.etickets.dtos.responses.ApiResponse;
import com.ivancoria.etickets.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/me/profile")
    public ResponseEntity<ApiResponse<CustomerProfileDTO>> getMyProfile(Authentication authentication) {
        final CustomerProfileDTO customerProfileDTO = customerService.getMyProfile(authentication);
        return ResponseEntity.ok(ApiResponse
                .success(HttpStatus.OK.value(), "Datos del usuario", customerProfileDTO));
    }
}
