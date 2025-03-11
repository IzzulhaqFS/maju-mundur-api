package com.enigmacamp.maju_mundur.controller;

import com.enigmacamp.maju_mundur.dto.request.auth.AuthRequest;
import com.enigmacamp.maju_mundur.dto.response.auth.AuthResponse;
import com.enigmacamp.maju_mundur.dto.response.common.CommonResponse;
import com.enigmacamp.maju_mundur.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register/merchant")
    public ResponseEntity<CommonResponse<AuthResponse>> registerMerchant(@RequestBody AuthRequest request) {
        AuthResponse authResponse = authService.registerMerchant(request);

        CommonResponse<AuthResponse> response = CommonResponse.<AuthResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Merchant account created.")
                .data(authResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/register/customer")
    public ResponseEntity<CommonResponse<AuthResponse>> registerCustomer(@RequestBody AuthRequest request) {
        AuthResponse authResponse = authService.registerMerchant(request);

        CommonResponse<AuthResponse> response = CommonResponse.<AuthResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Customer account created.")
                .data(authResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<AuthResponse>> login(@RequestBody AuthRequest request) {
        AuthResponse authResponse = authService.registerMerchant(request);

        CommonResponse<AuthResponse> response = CommonResponse.<AuthResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Login success.")
                .data(authResponse)
                .build();

        return ResponseEntity.ok(response);
    }
}
