package com.enigmacamp.maju_mundur.services;

import com.enigmacamp.maju_mundur.dto.request.transaction.NewTransactionRequest;
import com.enigmacamp.maju_mundur.dto.response.transaction.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse create(NewTransactionRequest request);
    TransactionResponse getById(String id);
    List<TransactionResponse> getAll();
}
