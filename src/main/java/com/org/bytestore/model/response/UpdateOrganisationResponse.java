package com.org.bytestore.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateOrganisationResponse {

    private Boolean success;

    private String message;

}
