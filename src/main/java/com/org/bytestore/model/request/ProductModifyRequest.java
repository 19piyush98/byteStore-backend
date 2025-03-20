package com.org.bytestore.model.request;

import com.org.bytestore.enums.ProductStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class ProductModifyRequest {

    private String sessionToken;

    private String userId;

    private String orgTokenId;

    private String productName;

    private String description;

    private String category;

    private String subcategory;

    private String brand;

    private double price;

    private int quantity;

    private Double discount;

    private Map<String, Object> attributes;

    private List<String> images;

    private List<String> tags;

    private ProductStatus status;
}
