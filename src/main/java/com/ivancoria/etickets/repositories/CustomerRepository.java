package com.ivancoria.etickets.repositories;

import com.ivancoria.etickets.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findById (long id);

    boolean existsByEmail(String email);

}
