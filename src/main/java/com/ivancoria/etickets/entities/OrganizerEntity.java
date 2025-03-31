package com.ivancoria.etickets.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "organizers")
public class OrganizerEntity extends UserEntity {

    @Column(nullable = false, length = 32)
    private String name;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventEntity> events;


}
