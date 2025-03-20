package com.org.bytestore.model.request;

import com.org.bytestore.enums.OrganisationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class AddOrganisationRequest {

    private String orgName;

    private String email;

    private Long phoneNo;

    private String city;

    private String street;

    private String state;

    private Long zipCode;

    private String country;

    private String website;

    private String description;

    private String orgTokenId;

    private OrganisationStatus status;

    @NonNull
    private String userId;

    @NonNull
    private String sessionToken;
}
