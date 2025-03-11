package com.enigmacamp.maju_mundur.utils;

import com.enigmacamp.maju_mundur.dto.response.merchant.MerchantResponse;
import com.enigmacamp.maju_mundur.entities.Merchant;

public class MerchantMapper {
    public static Merchant merchantResponseToMerchant(MerchantResponse merchantResponse) {
        return Merchant.builder()
                .id(merchantResponse.getId())
                .name(merchantResponse.getName())
                .phone(merchantResponse.getPhone())
                .email(merchantResponse.getEmail())
                .address(merchantResponse.getAddress())
                .build();
    }
}
