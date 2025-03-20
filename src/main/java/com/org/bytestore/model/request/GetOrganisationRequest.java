package com.org.bytestore.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetOrganisationRequest {

    private String orgTokenId;
}
