package com.org.bytestore.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateProductResponse {

    private boolean success;

    private String productId;

    private Long updatedAtEpoch;

    private String message;
}
