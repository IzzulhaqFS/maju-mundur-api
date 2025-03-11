package com.enigmacamp.maju_mundur.repositories;

import com.enigmacamp.maju_mundur.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
