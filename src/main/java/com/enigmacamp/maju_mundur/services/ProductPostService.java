package com.enigmacamp.maju_mundur.services;

import com.enigmacamp.maju_mundur.dto.request.product_post.NewProductPostRequest;
import com.enigmacamp.maju_mundur.dto.request.product_post.UpdateProductPostRequest;
import com.enigmacamp.maju_mundur.dto.response.product_post.ProductPostResponse;

import java.util.List;

public interface ProductPostService {
    ProductPostResponse create(NewProductPostRequest request);
    ProductPostResponse getById(String id);
    List<ProductPostResponse> getAll();
    ProductPostResponse update(UpdateProductPostRequest request);
    void delete(String id);
}
