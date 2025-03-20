package com.org.bytestore.validator.request;

import com.org.bytestore.model.entity.User;
import com.org.bytestore.model.request.AddUserRequest;
import com.org.bytestore.model.request.SignUpRequest;
import com.org.bytestore.model.validation.ValidationResult;
import com.org.bytestore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SignUpRequestValidator {

    private final UserRepository userRepository;

    public ValidationResult validate(final SignUpRequest request) {
        final StringBuilder validationResult = new StringBuilder();
        validateIfUserExist(request.getEmail()).ifPresent(reason-> validationResult.append(reason.getValidationFailureReason()));
        validateEmail(request.getEmail()).ifPresent(reason-> validationResult.append(reason.getValidationFailureReason()));
        validatePassword(request.getPassword()).ifPresent(reason-> validationResult.append(reason.getValidationFailureReason()));
        if (!validationResult.isEmpty()) {
            return ValidationResult.builder()
                    .isValid(Boolean.FALSE)
                    .validationFailureReason(String.valueOf(validationResult))
                    .build();
        }
        return ValidationResult.builder().isValid(Boolean.TRUE).build();
    }

    public ValidationResult validate(final AddUserRequest request) {
        final StringBuilder validationResult = new StringBuilder();
        validateIfUserExist(request.getEmail()).ifPresent(reason-> validationResult.append(reason.getValidationFailureReason()));
        validateEmail(request.getEmail()).ifPresent(reason-> validationResult.append(reason.getValidationFailureReason()));
        validatePassword(request.getPassword()).ifPresent(reason-> validationResult.append(reason.getValidationFailureReason()));
        if (!validationResult.isEmpty()) {
            return ValidationResult.builder()
                    .isValid(Boolean.FALSE)
                    .validationFailureReason(String.valueOf(validationResult))
                    .build();
        }
        return ValidationResult.builder().isValid(Boolean.TRUE).build();
    }

    private Optional<ValidationResult> validateIfUserExist(final String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return Optional.of(ValidationResult.builder()
                    .isValid(Boolean.FALSE)
                    .validationFailureReason("User with given email already present...")
                    .build());
        }
        return Optional.empty();
    }

    private Optional<ValidationResult> validatePassword(final String password) {
        final String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        if (!password.matches(regex)) {
            return Optional.of(ValidationResult.builder()
                    .isValid(Boolean.FALSE)
                    .validationFailureReason("Password too weak...")
                    .build());
        }
        return Optional.empty();
    }

    private Optional<ValidationResult> validateEmail(final String email) {
        final String regex = "^[a-zA-Z0-9_.±]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$";
        if (!email.matches(regex)) {
            return Optional.of(ValidationResult.builder()
                    .isValid(Boolean.FALSE)
                    .validationFailureReason("Email is invalid...")
                    .build());
        }
        return Optional.empty();
    }
}
