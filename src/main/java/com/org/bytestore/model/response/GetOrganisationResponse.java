package com.org.bytestore.model.response;

import com.org.bytestore.model.entity.Organisation;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetOrganisationResponse {

    private Organisation org;

    private String message;

    private  Boolean success;
}
