package com.ivancoria.etickets.repositories;

import com.ivancoria.etickets.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    EventEntity findById(long id);

    List<EventEntity> findByOrganizerEmail(String email);

    List<EventEntity> findByOrganizerId(long id);


}
