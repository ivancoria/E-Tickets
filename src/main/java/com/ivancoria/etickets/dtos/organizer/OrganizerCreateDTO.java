package com.ivancoria.etickets.dtos.organizer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizerCreateDTO {

    @NotEmpty(message = "Correo requerido")
    @Email(message = "Formato de correo inválido")
    @Size(max = 256, message = "El correo no puede tener más de 256 caracteres")
    private String email;

    @NotEmpty(message = "Nombre del Organizador o Productora requerido")
    @Size(min = 2, max = 32, message = "El nombre del Organizador o Productora debe tener entre 2 y 32 caracteres")
    private String name;

    @NotEmpty(message = "Contraseña requerida")
    @Size(min = 8, max = 128, message = "La contraseña debe tener entre 8 y 128 caracteres")
    private String password;

    @NotEmpty(message = "Repetir Contraseña requerida")
    @Size(min = 8, max = 128, message = "La contraseña debe tener entre 8 y 128 caracteres")
    private String confirmPassword;

}
