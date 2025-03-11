package com.enigmacamp.maju_mundur.services.impl;

import com.enigmacamp.maju_mundur.dto.request.transaction.NewTransactionRequest;
import com.enigmacamp.maju_mundur.dto.response.customer.CustomerResponse;
import com.enigmacamp.maju_mundur.dto.response.product_post.ProductPostResponse;
import com.enigmacamp.maju_mundur.dto.response.transaction.TransactionDetailResponse;
import com.enigmacamp.maju_mundur.dto.response.transaction.TransactionResponse;
import com.enigmacamp.maju_mundur.entities.Customer;
import com.enigmacamp.maju_mundur.entities.ProductPost;
import com.enigmacamp.maju_mundur.entities.Transaction;
import com.enigmacamp.maju_mundur.entities.TransactionDetail;
import com.enigmacamp.maju_mundur.repositories.TransactionRepository;
import com.enigmacamp.maju_mundur.services.*;
import com.enigmacamp.maju_mundur.utils.CustomerMapper;
import com.enigmacamp.maju_mundur.utils.MerchantMapper;
import com.enigmacamp.maju_mundur.utils.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;
    private final TransactionDetailService transactionDetailService;
    private final ProductPostService productPostService;
    private final ProductService productService;
    private final MerchantService merchantService;

    @Override
    public TransactionResponse create(NewTransactionRequest request) {
        CustomerResponse customerResponse = customerService.getById(request.getCustomerId());
        Customer customer = CustomerMapper.customerResponseToCustomer(customerResponse);

        Transaction transaction = Transaction.builder()
                .customer(customer)
                .transactionDate(new Date())
                .build();

        transactionRepository.saveAndFlush(transaction);

        List<TransactionDetail> transactionDetails = request.getRequests().stream().map(detailRequest -> {
            ProductPostResponse productPostResponse = productPostService.getById(detailRequest.getPostId());
            ProductPost productPost = ProductPost.builder()
                    .id(productPostResponse.getId())
                    .product(ProductMapper.productResponseToProduct(productService.getById(productPostResponse.getProductId())))
                    .merchant(MerchantMapper.merchantResponseToMerchant(merchantService.getById(productPostResponse.getMerchantId())))
                    .createdAt(productPostResponse.getCreatedAt())
                    .updatedAt(productPostResponse.getUpdatedAt())
                    .build();

            return TransactionDetail.builder()
                    .productPost(productPost)
                    .transaction(transaction)
                    .quantity(detailRequest.getQuantity())
                    .build();
        }).toList();

        transactionDetailService.createBulk(transactionDetails);
        transaction.setTransactionDetails(transactionDetails);

        List<TransactionDetailResponse> detailResponses = getTransactionDetailResponses(transactionDetails);

        Double totalPrice = getTotalPrice(detailResponses);

        return TransactionResponse.builder()
                .id(transaction.getId())
                .customerId(transaction.getCustomer().getId())
                .transactionDate(transaction.getTransactionDate())
                .detailResponses(detailResponses)
                .totalPrice(totalPrice)
                .build();
    }

    @Override
    public TransactionResponse getById(String id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found."));

        List<TransactionDetailResponse> detailResponses = getTransactionDetailResponses(transaction.getTransactionDetails());

        Double totalPrice = getTotalPrice(detailResponses);

        return TransactionResponse.builder()
                .id(transaction.getId())
                .customerId(transaction.getCustomer().getId())
                .transactionDate(transaction.getTransactionDate())
                .detailResponses(detailResponses)
                .totalPrice(totalPrice)
                .build();
    }

    @Override
    public List<TransactionResponse> getAll() {
        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream().map(transaction -> {
            List<TransactionDetailResponse> detailResponses = getTransactionDetailResponses(transaction.getTransactionDetails());
            Double totalPrice = getTotalPrice(detailResponses);
            return TransactionResponse.builder()
                    .id(transaction.getId())
                    .customerId(transaction.getCustomer().getId())
                    .transactionDate(transaction.getTransactionDate())
                    .detailResponses(detailResponses)
                    .totalPrice(totalPrice)
                    .build();
        }).toList();
    }

    private List<TransactionDetailResponse> getTransactionDetailResponses(List<TransactionDetail> transactionDetails) {
        return transactionDetails.stream().map(detail -> TransactionDetailResponse.builder()
                .id(detail.getId())
                .merchantId(detail.getProductPost().getMerchant().getId())
                .productId(detail.getProductPost().getProduct().getId())
                .quantity(detail.getQuantity())
                .subTotal(detail.getQuantity() * detail.getProductPost().getProduct().getPrice())
                .build()).toList();
    }

    private Double getTotalPrice(List<TransactionDetailResponse> detailResponses) {
        return detailResponses.stream()
                .mapToDouble(TransactionDetailResponse::getSubTotal)
                .sum();
    }
}
