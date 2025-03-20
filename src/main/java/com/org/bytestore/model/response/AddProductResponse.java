package com.org.bytestore.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddProductResponse {

    private boolean success;

    private String message;

    private Long timeStamp;

    private String productId;
}
