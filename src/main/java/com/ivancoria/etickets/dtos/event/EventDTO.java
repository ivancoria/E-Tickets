package com.ivancoria.etickets.dtos.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EventDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotEmpty(message = "Nombre del evento requerido")
    @Size(min = 4, max = 64, message = "El nombre del evento debe tener entre 4 y 64 caracteres")
    private String name;

    @NotEmpty(message = "Descripción del evento requerido")
    @Size(min = 4, max = 128, message = "La descripción del evento debe tener entre 4 y 128 caracteres")
    private String description;

    @NotEmpty(message = "Ubicación del evento requerido")
    @Size(min = 4, max = 128, message = "La ubicación del evento debe tener entre 4 y 128 caracteres")
    private String location;

    @NotEmpty(message = "Fecha del evento requerida")
    //@Future(message = "La fecha del evento debe ser en el futuro")
    private String date;

    @NotNull(message = "Precio del evento requerido")
    @Positive(message = "El precio debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener como máximo 10 enteros y 2 decimales")
    private double price;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long organizerId;
}
