package com.ivancoria.etickets.dtos.organizer;

import com.ivancoria.etickets.entities.enums.UserRole;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizerProfileDTO {

    private String email;

    private UserRole role;

    private String name;
}
