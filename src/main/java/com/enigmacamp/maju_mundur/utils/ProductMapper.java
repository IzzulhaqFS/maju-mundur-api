package com.enigmacamp.maju_mundur.utils;

import com.enigmacamp.maju_mundur.dto.response.product.ProductResponse;
import com.enigmacamp.maju_mundur.entities.Product;

public class ProductMapper {
    public static Product productResponseToProduct(ProductResponse productResponse) {
        return Product.builder()
                .id(productResponse.getId())
                .name(productResponse.getName())
                .description(productResponse.getDescription())
                .price(productResponse.getPrice())
                .stock(productResponse.getStock())
                .build();
    }
}
