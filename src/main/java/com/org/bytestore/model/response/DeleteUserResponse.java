package com.org.bytestore.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteUserResponse {

    private String message;

    private Boolean success;
}
