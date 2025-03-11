package com.enigmacamp.maju_mundur.services.impl;

import com.enigmacamp.maju_mundur.dto.request.product_post.NewProductPostRequest;
import com.enigmacamp.maju_mundur.dto.request.product_post.UpdateProductPostRequest;
import com.enigmacamp.maju_mundur.dto.response.merchant.MerchantResponse;
import com.enigmacamp.maju_mundur.dto.response.product.ProductResponse;
import com.enigmacamp.maju_mundur.dto.response.product_post.ProductPostResponse;
import com.enigmacamp.maju_mundur.entities.Merchant;
import com.enigmacamp.maju_mundur.entities.Product;
import com.enigmacamp.maju_mundur.entities.ProductPost;
import com.enigmacamp.maju_mundur.repositories.ProductPostRepository;
import com.enigmacamp.maju_mundur.services.MerchantService;
import com.enigmacamp.maju_mundur.services.ProductPostService;
import com.enigmacamp.maju_mundur.services.ProductService;
import com.enigmacamp.maju_mundur.utils.MerchantMapper;
import com.enigmacamp.maju_mundur.utils.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductPostServiceImpl implements ProductPostService {
    private final ProductPostRepository productPostRepository;
    private final ProductService productService;
    private final MerchantService merchantService;

    @Override
    public ProductPostResponse create(NewProductPostRequest request) {
        ProductResponse productResponse = productService.getById(request.getProductId());
        Product product = ProductMapper.productResponseToProduct(productResponse);

        MerchantResponse merchantResponse = merchantService.getById(request.getMerchantId());
        Merchant merchant = MerchantMapper.merchantResponseToMerchant(merchantResponse);

        ProductPost productPost = ProductPost.builder()
                .product(product)
                .merchant(merchant)
                .createdAt(new Date())
                .build();

        productPostRepository.saveAndFlush(productPost);

        return getProductPostResponse(productPost);
    }

    @Override
    public ProductPostResponse getById(String id) {
        ProductPost productPost = getProductPost(id);
        return getProductPostResponse(productPost);
    }

    @Override
    public List<ProductPostResponse> getAll() {
        List<ProductPost> productPosts = productPostRepository.findAll();
        return productPosts.stream().map(this::getProductPostResponse).toList();
    }

    @Override
    public ProductPostResponse update(UpdateProductPostRequest request) {
        ProductPost productPost = getProductPost(request.getId());

        ProductResponse productResponse = productService.getById(request.getProductId());
        Product product = ProductMapper.productResponseToProduct(productResponse);
        productPost.setProduct(product);

        MerchantResponse merchantResponse = merchantService.getById(request.getMerchantId());
        Merchant merchant = MerchantMapper.merchantResponseToMerchant(merchantResponse);
        productPost.setMerchant(merchant);

        productPost.setUpdatedAt(new Date());

        return getProductPostResponse(productPost);
    }

    @Override
    public void delete(String id) {
        ProductPost productPost = getProductPost(id);
        productPostRepository.delete(productPost);
    }

    private ProductPostResponse getProductPostResponse(ProductPost productPost) {
        return ProductPostResponse.builder()
                .id(productPost.getId())
                .productId(productPost.getProduct().getId())
                .merchantId(productPost.getMerchant().getId())
                .createdAt(productPost.getCreatedAt())
                .updatedAt(productPost.getUpdatedAt())
                .build();
    }

    private ProductPost getProductPost(String id) {
        return productPostRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post data not found."));
    }
}
