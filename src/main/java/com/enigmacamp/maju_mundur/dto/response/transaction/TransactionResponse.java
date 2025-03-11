package com.enigmacamp.maju_mundur.dto.response.transaction;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private String id;
    private Date transactionDate;
    private String customerId;
    private List<TransactionDetailResponse> detailResponses;
    private Double totalPrice;
}
