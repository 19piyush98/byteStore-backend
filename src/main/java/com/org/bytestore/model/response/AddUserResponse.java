package com.org.bytestore.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddUserResponse {

    private Boolean success;

    private String userId;

    private String orgTokenId;

    private Long createdAt;

    private Long updatedAt;

    private String message;
}
