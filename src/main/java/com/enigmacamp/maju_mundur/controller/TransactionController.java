package com.enigmacamp.maju_mundur.controller;

import com.enigmacamp.maju_mundur.dto.request.transaction.NewTransactionRequest;
import com.enigmacamp.maju_mundur.dto.response.common.CommonResponse;
import com.enigmacamp.maju_mundur.dto.response.transaction.TransactionResponse;
import com.enigmacamp.maju_mundur.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<CommonResponse<TransactionResponse>> create(@RequestBody NewTransactionRequest request) {
        TransactionResponse transactionResponse = transactionService.create(request);

        CommonResponse<TransactionResponse> response = CommonResponse.<TransactionResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Transaction created successfully.")
                .data(transactionResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<TransactionResponse>> getById(@PathVariable String id) {
        TransactionResponse transactionResponse = transactionService.getById(id);

        CommonResponse<TransactionResponse> response = CommonResponse.<TransactionResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get Transaction data successfully.")
                .data(transactionResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<TransactionResponse>>> getById() {
        List<TransactionResponse> transactionResponses = transactionService.getAll();

        CommonResponse<List<TransactionResponse>> response = CommonResponse.<List<TransactionResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all Transaction data successfully.")
                .data(transactionResponses)
                .build();

        return ResponseEntity.ok(response);
    }
}
