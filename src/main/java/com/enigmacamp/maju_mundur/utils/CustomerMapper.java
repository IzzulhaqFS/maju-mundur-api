package com.enigmacamp.maju_mundur.utils;

import com.enigmacamp.maju_mundur.dto.response.customer.CustomerResponse;
import com.enigmacamp.maju_mundur.entities.Customer;

public class CustomerMapper {
    public static Customer customerResponseToCustomer(CustomerResponse customerResponse) {
        return Customer.builder()
                .id(customerResponse.getId())
                .name(customerResponse.getName())
                .phone(customerResponse.getPhone())
                .email(customerResponse.getEmail())
                .address(customerResponse.getAddress())
                .point(customerResponse.getPoint())
                .build();
    }
}
