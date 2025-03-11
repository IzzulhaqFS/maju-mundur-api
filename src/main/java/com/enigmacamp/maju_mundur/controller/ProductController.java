package com.enigmacamp.maju_mundur.controller;

import com.enigmacamp.maju_mundur.dto.request.product.NewProductRequest;
import com.enigmacamp.maju_mundur.dto.request.product.UpdateProductRequest;
import com.enigmacamp.maju_mundur.dto.response.common.CommonResponse;
import com.enigmacamp.maju_mundur.dto.response.product.ProductResponse;
import com.enigmacamp.maju_mundur.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<CommonResponse<ProductResponse>> create(@RequestBody NewProductRequest request) {
        ProductResponse productResponse = productService.create(request);

        CommonResponse<ProductResponse> response = CommonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Product data created successfully.")
                .data(productResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ProductResponse>> getById(@PathVariable String id) {
        ProductResponse productResponse = productService.getById(id);

        CommonResponse<ProductResponse> response = CommonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get Product data successfully.")
                .data(productResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<ProductResponse>>> getAll() {
        List<ProductResponse> productResponses = productService.getAll();

        CommonResponse<List<ProductResponse>> response = CommonResponse.<List<ProductResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all Product data successfully.")
                .data(productResponses)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<ProductResponse>> update(@RequestBody UpdateProductRequest request) {
        ProductResponse productResponse = productService.update(request);

        CommonResponse<ProductResponse> response = CommonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Update Product data successfully.")
                .data(productResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> delete(@PathVariable String id) {
        productService.delete(id);

        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Delete Product data successfully.")
                .build();

        return ResponseEntity.ok(response);
    }
}
