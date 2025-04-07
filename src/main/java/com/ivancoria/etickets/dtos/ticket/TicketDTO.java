package com.ivancoria.etickets.dtos.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TicketDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String uniqueCode;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long customerId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long eventId;
}
