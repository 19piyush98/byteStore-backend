package com.org.bytestore.mapper.response;

import com.org.bytestore.model.entity.Product;
import com.org.bytestore.model.response.GetAllProductsResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductToGetAllProductsResponseMapper {

    public GetAllProductsResponse map(final List<Product> products){
        return GetAllProductsResponse.builder()
                .products(products)
                .success(Boolean.TRUE)
                .build();
    }
}
