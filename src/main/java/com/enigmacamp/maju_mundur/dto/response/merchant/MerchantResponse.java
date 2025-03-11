package com.enigmacamp.maju_mundur.dto.response.merchant;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MerchantResponse {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String address;
}
