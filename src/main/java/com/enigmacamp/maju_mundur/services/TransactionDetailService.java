package com.enigmacamp.maju_mundur.services;

import com.enigmacamp.maju_mundur.entities.TransactionDetail;

import java.util.List;

public interface TransactionDetailService {
    void createBulk(List<TransactionDetail> requests);
    List<TransactionDetail> getAllByTransaction(String transactionId);
}
