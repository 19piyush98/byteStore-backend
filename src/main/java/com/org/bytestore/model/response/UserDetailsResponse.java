package com.org.bytestore.model.response;

import com.org.bytestore.model.request.UserDetails;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDetailsResponse {

    private boolean success;

    private String message;

    private UserDetails user;

    private String orgName;

    private String unit;

    private String addedBy;

    private long lastUpdatedAt;

    private long timestamp;

}
