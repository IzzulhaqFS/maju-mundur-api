package com.enigmacamp.maju_mundur.repositories;

import com.enigmacamp.maju_mundur.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
