package com.enigmacamp.maju_mundur.dto.response.customer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Integer point;
}
