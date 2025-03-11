package com.enigmacamp.maju_mundur.controller;

import com.enigmacamp.maju_mundur.dto.request.customer.NewCustomerRequest;
import com.enigmacamp.maju_mundur.dto.request.customer.UpdateCustomerRequest;
import com.enigmacamp.maju_mundur.dto.response.common.CommonResponse;
import com.enigmacamp.maju_mundur.dto.response.customer.CustomerResponse;
import com.enigmacamp.maju_mundur.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CommonResponse<CustomerResponse>> create(@RequestBody NewCustomerRequest request) {
        CustomerResponse customerResponse = customerService.create(request);

        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Customer data created successfully.")
                .data(customerResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> getById(@PathVariable String id) {
        CustomerResponse customerResponse = customerService.getById(id);

        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get Customer data successfully.")
                .data(customerResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<CustomerResponse>>> getAll() {
        List<CustomerResponse> customerResponses = customerService.getAll();

        CommonResponse<List<CustomerResponse>> response = CommonResponse.<List<CustomerResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all Customer data successfully.")
                .data(customerResponses)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<CustomerResponse>> update(@RequestBody UpdateCustomerRequest request) {
        CustomerResponse customerResponse = customerService.update(request);

        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Update Customer data successfully.")
                .data(customerResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> delete(@PathVariable String id) {
        customerService.delete(id);

        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Delete Customer data successfully.")
                .build();

        return ResponseEntity.ok(response);
    }
}
