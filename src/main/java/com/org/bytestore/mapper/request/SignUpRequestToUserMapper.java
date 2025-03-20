package com.org.bytestore.mapper.request;

import com.org.bytestore.model.entity.User;
import com.org.bytestore.model.request.SignUpRequest;
import com.org.bytestore.utils.TokenUtils;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class SignUpRequestToUserMapper {

    public User map(@NonNull final SignUpRequest signUpRequest) {
        final String orgToken = TokenUtils.generateNewToken();
        final String userId = UUID.randomUUID().toString();
        final long currentEpoch = Instant.now().toEpochMilli();
        return User.builder()
                .userId(userId)
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .orgName(signUpRequest.getOrgName())
                .orgTokenId(orgToken)
                .createdAt(currentEpoch)
                .updatedAt(currentEpoch)
                .unit(signUpRequest.getUnit())
                .userDetails(signUpRequest.getUserDetails())
                .addedBy("Self")
                .build();
    }
}
