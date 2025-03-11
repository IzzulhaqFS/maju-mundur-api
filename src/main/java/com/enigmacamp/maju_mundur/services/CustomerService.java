package com.enigmacamp.maju_mundur.services;

import com.enigmacamp.maju_mundur.dto.request.customer.NewCustomerRequest;
import com.enigmacamp.maju_mundur.dto.request.customer.UpdateCustomerRequest;
import com.enigmacamp.maju_mundur.dto.response.customer.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(NewCustomerRequest request);
    CustomerResponse getById(String id);
    List<CustomerResponse> getAll();
    CustomerResponse update(UpdateCustomerRequest request);
    void delete(String id);
    void updatePoint(String id, Integer point);
}
