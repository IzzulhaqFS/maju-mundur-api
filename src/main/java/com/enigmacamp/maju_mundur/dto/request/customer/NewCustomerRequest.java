package com.enigmacamp.maju_mundur.dto.request.customer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCustomerRequest {
    private String name;
    private String phone;
    private String email;
    private String address;
}
