package com.org.bytestore.mapper.response;

import com.org.bytestore.model.entity.Organisation;
import com.org.bytestore.model.response.AddOrganisationResponse;
import org.springframework.stereotype.Component;

@Component
public class OrganisationToAddOrganisationResponseMapper {

    public AddOrganisationResponse map(final Organisation organisation){
        return AddOrganisationResponse.builder()
                .success(Boolean.TRUE)
                .createdAt(organisation.getCreatedAt())
                .updatedAt(organisation.getUpdatedAt())
                .message("Organisation added successfully...")
                .build();
    }
}
