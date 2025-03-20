package com.org.bytestore.mapper.request;

import com.org.bytestore.model.entity.Organisation;
import com.org.bytestore.model.request.AddOrganisationRequest;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class AddOrganisationRequestToOrganisationMapper {

    public Organisation map(@NonNull AddOrganisationRequest request) {
        final long currentEpoch = Instant.now().toEpochMilli();
        return Organisation.builder()
                .city(request.getCity())
                .country(request.getCountry())
                .email(request.getEmail())
                .state(request.getState())
                .orgTokenId(request.getOrgTokenId())
                .orgName(request.getOrgName())
                .createdAt(currentEpoch)
                .updatedAt(currentEpoch)
                .description(request.getDescription())
                .phoneNo(request.getPhoneNo())
                .street(request.getStreet())
                .website(request.getWebsite())
                .zipCode(request.getZipCode())
                .status(request.getStatus())
                .build();
    }

}
