package com.enigmacamp.maju_mundur.services;

import com.enigmacamp.maju_mundur.dto.request.merchant.NewMerchantRequest;
import com.enigmacamp.maju_mundur.dto.request.merchant.UpdateMerchantRequest;
import com.enigmacamp.maju_mundur.dto.response.merchant.MerchantResponse;

import java.util.List;

public interface MerchantService {
    MerchantResponse create(NewMerchantRequest request);
    MerchantResponse getById(String id);
    List<MerchantResponse> getAll();
    MerchantResponse update(UpdateMerchantRequest request);
    void delete(String id);
}
