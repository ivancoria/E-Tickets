package com.ivancoria.etickets.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {
    @NotEmpty(message = "Correo requerido")
    @Email(message = "Formato de correo inválido")
    @Size(max = 256, message = "El correo no puede tener más de 256 caracteres")
    private String email;

    @NotEmpty(message = "Contraseña requerida")
    @Size(min = 8, max = 128, message = "La contraseña debe tener entre 8 y 128 caracteres")
    private String password;

    @NotEmpty(message = "Confirmar Contraseña requerida")
    @Size(min = 8, max = 128, message = "La contraseña debe tener entre 8 y 128 caracteres")
    private String confirmPassword;
}
