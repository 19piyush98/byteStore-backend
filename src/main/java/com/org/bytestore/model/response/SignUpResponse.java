package com.org.bytestore.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponse {

    private String userId;

    private String sessionToken;

    private String orgTokenId;

    private Long expirationEpoch;

    private String message;

    private Boolean success;
}
