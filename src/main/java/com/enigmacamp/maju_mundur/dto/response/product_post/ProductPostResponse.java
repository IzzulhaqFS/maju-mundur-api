package com.enigmacamp.maju_mundur.dto.response.product_post;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPostResponse {
    private String id;
    private String productId;
    private String merchantId;
    private Date createdAt;
    private Date updatedAt;
}
