package com.org.bytestore.model.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteProductRequest {

    private String userId;

    private String orgTokenId;

    private String sessionTokenId;

    private String productId;

    private String productNameToBeDeleted;
}
