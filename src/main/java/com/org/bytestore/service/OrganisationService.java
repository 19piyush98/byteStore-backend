package com.org.bytestore.service;

import com.org.bytestore.audit.logbuilder.OrganisationLogBuilder;
import com.org.bytestore.mapper.request.AddOrganisationRequestToOrganisationMapper;
import com.org.bytestore.mapper.response.OrganisationToAddOrganisationResponseMapper;
import com.org.bytestore.model.entity.Organisation;
import com.org.bytestore.model.request.AddOrganisationRequest;
import com.org.bytestore.model.request.GetOrganisationRequest;
import com.org.bytestore.model.request.UpdateOrganisationRequest;
import com.org.bytestore.model.response.AddOrganisationResponse;
import com.org.bytestore.model.response.GetOrganisationResponse;
import com.org.bytestore.model.response.UpdateOrganisationResponse;
import com.org.bytestore.model.validation.ValidationResult;
import com.org.bytestore.repository.OrganisationRepository;
import com.org.bytestore.validator.request.RequestAuthValidator;
import com.org.bytestore.validator.request.RequestOrganisationValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrganisationService {

    private final OrganisationRepository organisationRepo;

    private final RequestOrganisationValidator requestOrganisationValidator;

    private final OrganisationLogBuilder organisationLogBuilder;

    public Optional<UpdateOrganisationResponse> updateOrganisation(@RequestBody UpdateOrganisationRequest request) {
        try {
            ValidationResult orgValidationResult = requestOrganisationValidator.validate(request);
                if (orgValidationResult.getIsValid()) {
                    request.getOrganisation().setUpdatedAt(Instant.now().toEpochMilli());
                    organisationRepo.save(request.getOrganisation());
                    organisationLogBuilder.buildAndPublish(request);
                    UpdateOrganisationResponse response =  UpdateOrganisationResponse.builder().success(Boolean.TRUE).build();
                    return Optional.of(response);
                }
            return Optional.of(UpdateOrganisationResponse.builder()
                    .success(Boolean.FALSE)
                    .message(orgValidationResult.getValidationFailureReason())
                    .build());
        } catch (final Exception err) {
            log.error("Unable to add organisation for {}, failed with exception", request, err);
        }
        return Optional.empty();
    }

    public GetOrganisationResponse getOrganisationResponse(@RequestBody GetOrganisationRequest request){
        Optional<Organisation> org = organisationRepo.findById(request.getOrgTokenId());
        if(org.isPresent()){
           return GetOrganisationResponse.builder()
                   .success(Boolean.TRUE)
                   .org(org.get())
                   .build();
        }
        return GetOrganisationResponse.builder()
                .success(Boolean.FALSE)
                .message("Unable to find Org Details...")
                .build();
    }
}
