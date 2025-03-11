package com.enigmacamp.maju_mundur.services.impl;

import com.enigmacamp.maju_mundur.dto.request.product.NewProductRequest;
import com.enigmacamp.maju_mundur.dto.request.product.UpdateProductRequest;
import com.enigmacamp.maju_mundur.dto.response.product.ProductResponse;
import com.enigmacamp.maju_mundur.entities.Product;
import com.enigmacamp.maju_mundur.repositories.ProductRepository;
import com.enigmacamp.maju_mundur.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse create(NewProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();

        productRepository.saveAndFlush(product);

        return getProductResponse(product);
    }

    @Override
    public ProductResponse getById(String id) {
        Product product = getProduct(id);
        return getProductResponse(product);
    }

    @Override
    public List<ProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::getProductResponse).toList();
    }

    @Override
    public ProductResponse update(UpdateProductRequest request) {
        Product product = getProduct(request.getId());

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        productRepository.saveAndFlush(product);

        return getProductResponse(product);
    }

    @Override
    public void delete(String id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }

    private ProductResponse getProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

    private Product getProduct(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product data not found."));
    }
}
