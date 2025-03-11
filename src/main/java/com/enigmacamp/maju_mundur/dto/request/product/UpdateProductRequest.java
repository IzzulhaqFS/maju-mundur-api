package com.enigmacamp.maju_mundur.dto.request.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductRequest {
    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
