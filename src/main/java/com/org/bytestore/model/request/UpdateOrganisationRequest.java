package com.org.bytestore.model.request;

import com.org.bytestore.model.entity.Organisation;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateOrganisationRequest {

    private String userId;

    private Organisation organisation;
}
