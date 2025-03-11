package com.enigmacamp.maju_mundur.repositories;

import com.enigmacamp.maju_mundur.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query(value = "UPDATE m_customer SET point = :newPoint WHERE id = :customerId", nativeQuery = true)
    @Modifying
    @Transactional
    void updatePoint(
            @Param("customerId") String customerId,
            @Param("newPoint") Integer newPoint
    );
}
