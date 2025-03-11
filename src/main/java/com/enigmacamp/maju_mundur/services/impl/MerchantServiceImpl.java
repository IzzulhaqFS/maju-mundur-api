package com.enigmacamp.maju_mundur.services.impl;

import com.enigmacamp.maju_mundur.dto.request.merchant.NewMerchantRequest;
import com.enigmacamp.maju_mundur.dto.request.merchant.UpdateMerchantRequest;
import com.enigmacamp.maju_mundur.dto.response.merchant.MerchantResponse;
import com.enigmacamp.maju_mundur.entities.Merchant;
import com.enigmacamp.maju_mundur.repositories.MerchantRepository;
import com.enigmacamp.maju_mundur.services.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;

    @Override
    public MerchantResponse create(NewMerchantRequest request) {
        Merchant merchant = Merchant.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .build();

        merchantRepository.saveAndFlush(merchant);

        return getMerchantResponse(merchant);
    }

    @Override
    public MerchantResponse getById(String id) {
        Merchant merchant = getMerchant(id);

        return getMerchantResponse(merchant);
    }

    @Override
    public List<MerchantResponse> getAll() {
        List<Merchant> merchants = merchantRepository.findAll();

        return merchants.stream().map(this::getMerchantResponse).toList();
    }

    @Override
    public MerchantResponse update(UpdateMerchantRequest request) {
        Merchant merchant = getMerchant(request.getId());

        merchant.setName(request.getName());
        merchant.setPhone(request.getPhone());
        merchant.setEmail(request.getEmail());
        merchant.setAddress(request.getAddress());

        merchantRepository.saveAndFlush(merchant);

        return getMerchantResponse(merchant);
    }

    @Override
    public void delete(String id) {
        Merchant merchant = getMerchant(id);

        merchantRepository.delete(merchant);
    }

    private MerchantResponse getMerchantResponse(Merchant merchant) {
        return MerchantResponse.builder()
                .id(merchant.getId())
                .name(merchant.getName())
                .phone(merchant.getPhone())
                .email(merchant.getEmail())
                .address(merchant.getAddress())
                .build();
    }

    private Merchant getMerchant(String id) {
        return merchantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Merchant data not found."));
    }
}
