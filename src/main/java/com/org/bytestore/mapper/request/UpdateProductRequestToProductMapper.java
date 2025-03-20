package com.org.bytestore.mapper.request;

import com.org.bytestore.model.entity.Product;
import com.org.bytestore.model.request.UpdateProductRequest;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UpdateProductRequestToProductMapper {

    public Product map(@NonNull final UpdateProductRequest request) {
        final Long currentEpoch = Instant.now().toEpochMilli();
        return Product.builder()
                .productId(request.getProductId())
                .updatedAt(currentEpoch)
                .description(request.getProductModifyRequest().getDescription())
                .brand(request.getProductModifyRequest().getBrand())
                .price(request.getProductModifyRequest().getPrice())
                .attributes(request.getProductModifyRequest().getAttributes())
                .productName(request.getProductModifyRequest().getProductName())
                .category(request.getProductModifyRequest().getCategory())
                .tags(request.getProductModifyRequest().getTags())
                .images(request.getProductModifyRequest().getImages())
                .description(request.getProductModifyRequest().getDescription())
                .orgTokenId(request.getProductModifyRequest().getOrgTokenId())
                .quantity(request.getProductModifyRequest().getQuantity())
                .status(request.getProductModifyRequest().getStatus())
                .discount(request.getProductModifyRequest().getDiscount())
                .build();
    }
}
