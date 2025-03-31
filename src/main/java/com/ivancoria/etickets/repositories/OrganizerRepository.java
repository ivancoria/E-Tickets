package com.ivancoria.etickets.repositories;

import com.ivancoria.etickets.entities.OrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<OrganizerEntity, Long> {

    OrganizerEntity findById(long id);

}
