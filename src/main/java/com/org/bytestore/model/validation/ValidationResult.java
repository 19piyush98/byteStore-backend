package com.org.bytestore.model.validation;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ValidationResult {

    private Boolean isValid;

    private String validationFailureReason;
}