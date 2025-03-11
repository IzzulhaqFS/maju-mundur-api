package com.enigmacamp.maju_mundur.dto.request.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewProductRequest {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
