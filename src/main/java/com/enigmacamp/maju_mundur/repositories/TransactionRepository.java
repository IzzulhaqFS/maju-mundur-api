package com.enigmacamp.maju_mundur.repositories;

import com.enigmacamp.maju_mundur.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
