package com.org.bytestore.model.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteUserRequest {

    private String principleUserId;

    private String userNameToBeDeleted;

    private String userId;

    private String sessionTokenId;

    private String orgTokenId;
}
