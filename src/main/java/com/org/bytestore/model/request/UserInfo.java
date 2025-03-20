package com.org.bytestore.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserInfo {

    private String preferredLanguage;

    private Long joinedDate;

    private Double loyaltyPoints;

}