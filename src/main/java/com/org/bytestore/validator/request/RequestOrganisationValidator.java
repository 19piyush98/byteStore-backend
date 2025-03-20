package com.org.bytestore.validator.request;

import com.org.bytestore.component.Authentication;
import com.org.bytestore.model.entity.Organisation;
import com.org.bytestore.model.request.AddOrganisationRequest;
import com.org.bytestore.model.request.UpdateOrganisationRequest;
import com.org.bytestore.model.validation.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestOrganisationValidator {

    private final Authentication authentication;

    public ValidationResult validate(final UpdateOrganisationRequest request) {
        if (authentication.isOrganised(request.getOrganisation().getOrgTokenId())) {
            return ValidationResult.builder()
                    .isValid(Boolean.TRUE)
                    .build();
        }
        return ValidationResult.builder()
                .isValid(Boolean.FALSE)
                .validationFailureReason("User not authorised, please log in again...")
                .build();
    }
}
