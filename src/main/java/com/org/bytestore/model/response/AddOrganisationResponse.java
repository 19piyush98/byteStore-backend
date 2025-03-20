package com.org.bytestore.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddOrganisationResponse {

    private Boolean success;

    private String message;

    private Long createdAt;

    private Long updatedAt;
}
