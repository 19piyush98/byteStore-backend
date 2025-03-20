package com.org.bytestore.model.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpRequest {

    private UserDetails userDetails;

    private String orgName;

    private String unit;

    private String email;

    private String password;
}