package com.ivancoria.etickets.dtos.customer;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateDTO {

    @Email(message = "Formato de Email inválido")
    @Size(max = 256, message = "El correo no puede tener más de 256 caracteres")
    private String email;

    @Size(max = 32, message = "El nombre no puede tener mas de 32 caracteres")
    private String firstName;

    @Size(min = 2, max = 32, message = "El apellido no puede tener mas de 32 caracteres")
    private String lastName;

    @Pattern(regexp = "MALE|FEMALE|OTHER", message = "El género debe ser MALE, FEMALE o OTHER")
    private String gender;
}
