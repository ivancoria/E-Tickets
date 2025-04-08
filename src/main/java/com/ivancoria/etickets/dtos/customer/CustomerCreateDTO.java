package com.ivancoria.etickets.dtos.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ivancoria.etickets.entities.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateDTO {

    @NotEmpty(message = "Email requerido")
    @Email(message = "Formato de Email inválido")
    @Size(max = 256, message = "El correo no puede tener más de 256 caracteres")
    private String email;

    @NotEmpty(message = "Nombre requerido")
    @Size(min = 2, max = 32, message = "El nombre debe tener entre 2 y 32 caracteres")
    private String firstName;

    @NotEmpty(message = "Apellido requerido")
    @Size(min = 2, max = 32, message = "El apellido debe tener entre 2 y 32 caracteres")
    private String lastName;

    @NotEmpty(message = "Contraseña requerida")
    @Size(min = 8, max = 128, message = "La contraseña debe tener entre 8 y 128 caracteres")
    private String password;

    @NotEmpty(message = "Repetir Contraseña requerida")
    @Size(min = 8, max = 128, message = "La contraseña debe tener entre 8 y 128 caracteres")
    private String confirmPassword;

    @NotEmpty(message = "Genero requerido")
    @Pattern(regexp = "MALE|FEMALE|OTHER", message = "El género debe ser MALE, FEMALE o OTHER")
    private String gender;
}
