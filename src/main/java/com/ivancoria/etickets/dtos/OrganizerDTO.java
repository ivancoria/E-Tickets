package com.ivancoria.etickets.dtos;

import com.ivancoria.etickets.entities.EventEntity;
import com.ivancoria.etickets.entities.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrganizerDTO {

    private Long id;

    @NotEmpty(message = "Correo requerido")
    @Email(message = "Formato de correo inválido")
    @Size(max = 256, message = "El correo no puede tener más de 256 caracteres")
    private String email;

    @NotEmpty(message = "Contraseña requerida")
    @Size(min = 8, max = 128, message = "La contraseña debe tener entre 8 y 128 caracteres")
    private String password;

    @NotEmpty(message = "Repetir Contraseña requerida")
    @Size(min = 8, max = 128, message = "La contraseña debe tener entre 8 y 128 caracteres")
    private String confirmPassword;

    private UserRole role;

    @NotEmpty(message = "Nombre del Organizador o Productora requerido")
    @Size(min = 2, max = 32, message = "El nombre del Organizador o Productora debe tener entre 2 y 32 caracteres")
    private String name;

    private List<EventEntity> events;
}
