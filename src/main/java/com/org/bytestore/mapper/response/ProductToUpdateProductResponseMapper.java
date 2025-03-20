package com.org.bytestore.mapper.response;

import com.org.bytestore.model.entity.Product;
import com.org.bytestore.model.response.UpdateProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductToUpdateProductResponseMapper {

    public UpdateProductResponse map(final Product product) {
        return UpdateProductResponse.builder()
                .productId(product.getProductId())
                .updatedAtEpoch(product.getUpdatedAt())
                .success(Boolean.TRUE)
                .build();
    }
}
