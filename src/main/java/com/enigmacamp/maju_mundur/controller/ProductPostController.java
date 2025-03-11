package com.enigmacamp.maju_mundur.controller;

import com.enigmacamp.maju_mundur.dto.request.product_post.NewProductPostRequest;
import com.enigmacamp.maju_mundur.dto.request.product_post.UpdateProductPostRequest;
import com.enigmacamp.maju_mundur.dto.response.common.CommonResponse;
import com.enigmacamp.maju_mundur.dto.response.product_post.ProductPostResponse;
import com.enigmacamp.maju_mundur.services.ProductPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/posts")
public class ProductPostController {
    private final ProductPostService productPostService;

    @PostMapping
    public ResponseEntity<CommonResponse<ProductPostResponse>> create(@RequestBody NewProductPostRequest request) {
        ProductPostResponse productPostResponse = productPostService.create(request);

        CommonResponse<ProductPostResponse> response = CommonResponse.<ProductPostResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Post data created successfully.")
                .data(productPostResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ProductPostResponse>> getById(@PathVariable String id) {
        ProductPostResponse productPostResponse = productPostService.getById(id);

        CommonResponse<ProductPostResponse> response = CommonResponse.<ProductPostResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get Post data successfully.")
                .data(productPostResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<ProductPostResponse>>> getAll() {
        List<ProductPostResponse> productPostResponses = productPostService.getAll();

        CommonResponse<List<ProductPostResponse>> response = CommonResponse.<List<ProductPostResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all Post data successfully.")
                .data(productPostResponses)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<ProductPostResponse>> update(@RequestBody UpdateProductPostRequest request) {
        ProductPostResponse productPostResponse = productPostService.update(request);

        CommonResponse<ProductPostResponse> response = CommonResponse.<ProductPostResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Update Post data successfully.")
                .data(productPostResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> delete(@PathVariable String id) {
        productPostService.delete(id);

        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Delete Post data successfully.")
                .build();

        return ResponseEntity.ok(response);
    }
}
