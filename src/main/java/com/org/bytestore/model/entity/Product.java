package com.org.bytestore.model.entity;

import com.org.bytestore.enums.ProductStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@Document("Product")
public class Product {

    @Id
    private String productId;

    private String productName;

    private String description;

    private String category;

    private String brand;

    private Double price;

    private Integer quantity;

    private Double discount;

    private Map<String, Object> attributes;

    private List<String> images;

    private List<String> tags;

    private ProductStatus status;

    private Long createdAt;

    private Long updatedAt;

    private String orgTokenId;

}
