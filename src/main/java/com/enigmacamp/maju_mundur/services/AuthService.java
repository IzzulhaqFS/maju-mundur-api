package com.enigmacamp.maju_mundur.services;

import com.enigmacamp.maju_mundur.dto.request.auth.AuthRequest;
import com.enigmacamp.maju_mundur.dto.response.auth.AuthResponse;

public interface AuthService {
    AuthResponse registerMerchant(AuthRequest request);
    AuthResponse registerCustomer(AuthRequest request);
    AuthResponse login(AuthRequest request);
}
