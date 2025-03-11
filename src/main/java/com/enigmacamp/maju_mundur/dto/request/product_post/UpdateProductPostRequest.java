package com.enigmacamp.maju_mundur.dto.request.product_post;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductPostRequest {
    private String id;
    private String productId;
    private String merchantId;
}
