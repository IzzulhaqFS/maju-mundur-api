package com.enigmacamp.maju_mundur.services.impl;

import com.enigmacamp.maju_mundur.entities.TransactionDetail;
import com.enigmacamp.maju_mundur.repositories.TransactionDetailRepository;
import com.enigmacamp.maju_mundur.services.TransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionDetailServiceImpl implements TransactionDetailService {
    private final TransactionDetailRepository transactionDetailRepository;

    @Override
    public void createBulk(List<TransactionDetail> requests) {
        transactionDetailRepository.saveAllAndFlush(requests);
    }

    @Override
    public List<TransactionDetail> getAllByTransaction(String transactionId) {
        return transactionDetailRepository.getAllByTransaction(transactionId);
    }
}
