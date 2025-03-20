package com.org.bytestore.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetAllProductsRequest {

    private String userId;

    private String orgTokenId;

    private String sessionToken;
}
