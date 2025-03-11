package com.enigmacamp.maju_mundur.services;

import com.enigmacamp.maju_mundur.dto.request.product.NewProductRequest;
import com.enigmacamp.maju_mundur.dto.request.product.UpdateProductRequest;
import com.enigmacamp.maju_mundur.dto.response.product.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(NewProductRequest request);
    ProductResponse getById(String id);
    List<ProductResponse> getAll();
    ProductResponse update(UpdateProductRequest request);
    void delete(String id);
}
