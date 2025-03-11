package com.enigmacamp.maju_mundur.dto.request.merchant;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMerchantRequest {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String address;
}
