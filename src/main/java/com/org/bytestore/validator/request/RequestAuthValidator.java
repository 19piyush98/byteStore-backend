package com.org.bytestore.validator.request;

import com.org.bytestore.component.Authentication;
import com.org.bytestore.model.entity.Organisation;
import com.org.bytestore.model.request.AddOrganisationRequest;
import com.org.bytestore.model.request.AddUserRequest;
import com.org.bytestore.model.request.ProductModifyRequest;
import com.org.bytestore.model.validation.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestAuthValidator {

    private final Authentication authentication;

    public ValidationResult validate(final ProductModifyRequest request) {
        if (authentication.isAuthorised(request.getUserId(), request.getSessionToken())) {
            return ValidationResult.builder()
                    .isValid(Boolean.TRUE)
                    .build();
        }
        return ValidationResult.builder()
                .isValid(Boolean.FALSE)
                .validationFailureReason("User not authorised, please log in again...")
                .build();
    }

    public ValidationResult validate(final AddUserRequest request) {
        if (authentication.isAuthorised(request.getPrincipleUserId(), request.getSessionTokenId())) {
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

// Isko polymorphism khete hai ya method overloading
