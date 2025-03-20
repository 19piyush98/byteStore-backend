package com.org.bytestore.mapper.request;

import com.org.bytestore.model.entity.User;
import com.org.bytestore.model.request.AddUserRequest;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class AddUserRequestToUserMapper {

    public User map(@NonNull AddUserRequest request) {
        final Long currentEpoch = Instant.now().toEpochMilli();
        return User.builder()
                .userDetails(request.getUserDetails())
                .userId(UUID.randomUUID().toString())
                .orgTokenId(request.getOrgTokenId())
                .email(request.getEmail())
                .createdAt(currentEpoch)
                .orgName(request.getOrgName())
                .password(request.getPassword())
                .unit(request.getUnit())
                .updatedAt(currentEpoch)
                .addedBy(request.getPrincipleUserId())
                .build();
    }
}
