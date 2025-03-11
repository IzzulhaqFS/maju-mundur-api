package com.enigmacamp.maju_mundur.dto.request.transaction;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewTransactionDetailRequest {
    private String postId;
    private Integer quantity;
}
