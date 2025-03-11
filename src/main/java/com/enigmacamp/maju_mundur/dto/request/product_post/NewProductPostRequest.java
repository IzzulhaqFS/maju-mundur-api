package com.enigmacamp.maju_mundur.dto.request.product_post;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewProductPostRequest {
    private String productId;
    private String merchantId;
}
