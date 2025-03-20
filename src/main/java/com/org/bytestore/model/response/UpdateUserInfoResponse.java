package com.org.bytestore.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateUserInfoResponse {

    private Boolean success;

    private String message;

    private String orgTokenId;

    private Long updatedAt;
}
