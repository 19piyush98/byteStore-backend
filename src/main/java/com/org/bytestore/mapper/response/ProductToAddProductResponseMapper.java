package com.org.bytestore.mapper.response;

import com.org.bytestore.model.entity.Product;
import com.org.bytestore.model.response.AddProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductToAddProductResponseMapper {

    public AddProductResponse map(final Product product){
        return AddProductResponse.builder()
                .productId(product.getProductId())
                .success(Boolean.TRUE)
                .timeStamp(product.getCreatedAt())
                .message("Product added successfully...")
                .build();
    }
}
