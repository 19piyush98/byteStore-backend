package com.org.bytestore.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class AddUserRequest {

    @NonNull
    private String principleUserId;

    @NonNull
    private String orgTokenId;

    @NonNull
    private String sessionTokenId;

    @NonNull
    private String email;

    @NonNull
    private String password;

    private String orgName;

    private String unit;

    private UserDetails userDetails;

}
