package com.ivancoria.etickets.entities.enums;

public enum UserRole {
    CUSTOMER,  // usuario que puede comprar tickets, pero no organizar eventos
    ORGANIZER  // usuario que puede crear eventos, pero no comprar tickets
}
