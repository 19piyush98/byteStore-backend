package com.org.bytestore.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteProductResponse {

    private Boolean success;

    private String message;
}
