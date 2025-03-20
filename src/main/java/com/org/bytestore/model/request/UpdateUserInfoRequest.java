package com.org.bytestore.model.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateUserInfoRequest {

    private String orgTokenId;

    private String sessionToken;

    private String userId;

    private UserDetails userDetails;

}
