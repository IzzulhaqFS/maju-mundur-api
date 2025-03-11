package com.enigmacamp.maju_mundur.repositories;

import com.enigmacamp.maju_mundur.entities.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, String> {

    @Query(value = "SELECT * FROM t_transaction_detail td WHERE td.transaction_id = :transactionId", nativeQuery = true)
    List<TransactionDetail> getAllByTransaction(@Param("transactionId") String transactionId);
}
