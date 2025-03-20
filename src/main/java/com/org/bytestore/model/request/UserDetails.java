package com.org.bytestore.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDetails {

    private UserInfo userInfo;

    private String userName;

    private Long phoneNo;

    private String email;
}
