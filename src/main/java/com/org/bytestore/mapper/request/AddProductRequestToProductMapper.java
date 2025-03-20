package com.org.bytestore.mapper.request;

import com.org.bytestore.model.entity.Product;
import com.org.bytestore.model.request.ProductModifyRequest;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class AddProductRequestToProductMapper {

    public Product map(@NonNull final ProductModifyRequest request) {
        final Long currentEpoch = Instant.now().toEpochMilli();
        return Product.builder()
                .productId(UUID.randomUUID().toString())
                .brand(request.getBrand())
                .price(request.getPrice())
                .attributes(request.getAttributes())
                .productName(request.getProductName())
                .category(request.getCategory())
                .tags(request.getTags())
                .images(request.getImages())
                .createdAt(currentEpoch)
                .description(request.getDescription())
                .orgTokenId(request.getOrgTokenId())
                .quantity(request.getQuantity())
                .status(request.getStatus())
                .discount(request.getDiscount())
                .updatedAt(currentEpoch)
                .build();
    }
}
