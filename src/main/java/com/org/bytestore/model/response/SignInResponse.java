package com.org.bytestore.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponse {

    private String userId;

    private String sessionToken;

    private boolean isValid;

    private long expirationEpoch;

    private String orgTokenId;

    private String message;
}
