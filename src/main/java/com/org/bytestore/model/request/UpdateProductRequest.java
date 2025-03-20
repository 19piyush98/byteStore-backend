package com.org.bytestore.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateProductRequest {

    private String productId;

    private ProductModifyRequest productModifyRequest;
}
