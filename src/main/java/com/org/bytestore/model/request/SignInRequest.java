package com.org.bytestore.model.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInRequest {

    private String email;

    private String password;
}
