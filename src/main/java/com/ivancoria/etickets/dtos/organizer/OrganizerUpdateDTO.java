package com.ivancoria.etickets.dtos.organizer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizerUpdateDTO {

    @Email(message = "Formato de Email inválido")
    @Size(max = 256, message = "El correo no puede tener más de 256 caracteres")
    private String email;

    @Size(max = 32, message = "El nombre no puede tener mas de 32 caracteres")
    private String name;

}
