package com.ivancoria.etickets.repositories;

import com.ivancoria.etickets.entities.EventEntity;
import com.ivancoria.etickets.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity,Long> {

    EventEntity findById(long id);

    List<TicketEntity> findByCustomerId(long id);
}
