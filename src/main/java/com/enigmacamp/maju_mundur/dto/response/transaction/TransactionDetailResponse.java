package com.enigmacamp.maju_mundur.dto.response.transaction;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetailResponse {
    private String id;
    private String productId;
    private String merchantId;
    private Integer quantity;
    private Double subTotal;
}
