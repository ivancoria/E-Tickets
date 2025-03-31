package com.ivancoria.etickets.entities;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String name;

    @Column(nullable = false, length = 128)
    private String description;

    @Column(nullable = false, length = 128)
    private String location;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private OrganizerEntity organizer;
}