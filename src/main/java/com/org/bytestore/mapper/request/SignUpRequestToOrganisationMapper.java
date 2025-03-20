package com.org.bytestore.mapper.request;

import com.org.bytestore.enums.OrganisationStatus;
import com.org.bytestore.model.entity.Organisation;
import com.org.bytestore.model.request.SignUpRequest;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SignUpRequestToOrganisationMapper {

    public Organisation map(final SignUpRequest request, final String orgTokenId){
        final Long currentEpoch = Instant.now().toEpochMilli();
        return Organisation.builder()
                .orgTokenId(orgTokenId)
                .orgName(request.getOrgName())
                .email(request.getEmail())
                .phoneNo(request.getUserDetails().getPhoneNo())
                .status(OrganisationStatus.ACTIVE)
                .createdAt(currentEpoch)
                .updatedAt(currentEpoch)
                .build();
    }
}
