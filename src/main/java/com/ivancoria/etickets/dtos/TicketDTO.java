package com.ivancoria.etickets.dtos;

import lombok.*;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TicketDTO {

    private Long id;

    private String uniqueCode;

    private Long customerId;

    private Long eventId;
}
