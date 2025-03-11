package com.enigmacamp.maju_mundur.controller;

import com.enigmacamp.maju_mundur.dto.request.merchant.NewMerchantRequest;
import com.enigmacamp.maju_mundur.dto.request.merchant.UpdateMerchantRequest;
import com.enigmacamp.maju_mundur.dto.response.common.CommonResponse;
import com.enigmacamp.maju_mundur.dto.response.merchant.MerchantResponse;
import com.enigmacamp.maju_mundur.services.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/merchants")
public class MerchantController {
    private final MerchantService merchantService;

    @PostMapping
    public ResponseEntity<CommonResponse<MerchantResponse>> create(
            @RequestBody NewMerchantRequest request
    ) {
        MerchantResponse merchantResponse = merchantService.create(request);

        CommonResponse<MerchantResponse> response = CommonResponse.<MerchantResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Merchant data created successfully.")
                .data(merchantResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<MerchantResponse>> getById(@PathVariable String id) {
        MerchantResponse merchantResponse = merchantService.getById(id);

        CommonResponse<MerchantResponse> response = CommonResponse.<MerchantResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get Merchant data successfully.")
                .data(merchantResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<MerchantResponse>>> getAll() {
        List<MerchantResponse> merchantResponses = merchantService.getAll();

        CommonResponse<List<MerchantResponse>> response = CommonResponse.<List<MerchantResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all Merchant data Successfully.")
                .data(merchantResponses)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<MerchantResponse>> update(
            @RequestBody UpdateMerchantRequest request
    ) {
        MerchantResponse merchantResponse = merchantService.update(request);

        CommonResponse<MerchantResponse> response = CommonResponse.<MerchantResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Update Merchant data successfully.")
                .data(merchantResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> delete(@PathVariable String id) {
        merchantService.delete(id);

        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Delete Merchant data successfully.")
                .build();

        return ResponseEntity.ok(response);
    }
}
