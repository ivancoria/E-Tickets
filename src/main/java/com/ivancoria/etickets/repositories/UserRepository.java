package com.ivancoria.etickets.repositories;

import com.ivancoria.etickets.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    /* Retorna el ID y no la entidad */
    @Query("SELECT o.id FROM UserEntity o WHERE o.email = :email")
    Long findIdByEmail(@Param("email") String email);
}
