package com.ivancoria.etickets.dtos.customer;

import com.ivancoria.etickets.entities.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProfileDTO {

    private String email;

    private String firstName;

    private String lastName;

    private UserRole role;
}
