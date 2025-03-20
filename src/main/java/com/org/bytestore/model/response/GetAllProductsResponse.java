package com.org.bytestore.model.response;

import com.org.bytestore.model.entity.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GetAllProductsResponse {

    private List<Product> products;

    private String message;

    private Boolean success;
}
