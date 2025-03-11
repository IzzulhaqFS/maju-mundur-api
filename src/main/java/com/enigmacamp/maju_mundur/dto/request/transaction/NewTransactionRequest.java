package com.enigmacamp.maju_mundur.dto.request.transaction;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewTransactionRequest {
    private String customerId;
    private List<NewTransactionDetailRequest> requests;
}
