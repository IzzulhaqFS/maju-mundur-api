package com.enigmacamp.maju_mundur.repositories;

import com.enigmacamp.maju_mundur.entities.ProductPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPostRepository extends JpaRepository<ProductPost, String> {
}
