package com.ivancoria.etickets.controllers;

import com.ivancoria.etickets.dtos.customer.CustomerProfileDTO;
import com.ivancoria.etickets.dtos.customer.CustomerUpdateDTO;
import com.ivancoria.etickets.dtos.responses.ApiResponse;
import com.ivancoria.etickets.dtos.ticket.TicketDTO;
import com.ivancoria.etickets.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
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

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PatchMapping("/me/update")
    public ResponseEntity<ApiResponse<CustomerProfileDTO>> updateCustomer(
            @RequestBody CustomerUpdateDTO customerUpdateDTO, Authentication authentication) {
        final CustomerProfileDTO customer = customerService.updateCustomer(customerUpdateDTO, authentication);
        return ResponseEntity.ok(ApiResponse
                .success(HttpStatus.OK.value(), "Datos del usuario actualizados", customer));

    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/me/tickets")
    public ResponseEntity<ApiResponse<List<TicketDTO>>> myTickets(Authentication authentication){
        final List<TicketDTO> myTickets = customerService.myTickets(authentication);
        return ResponseEntity.ok(ApiResponse
                .success(HttpStatus.OK.value(), "Lista de Tickets", myTickets));
    }
}
