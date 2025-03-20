package com.org.bytestore.mapper.response;

import com.org.bytestore.model.entity.User;
import com.org.bytestore.model.response.UserDetailsResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserToUserDetailsResponseMapper {

    public UserDetailsResponse map(final User users){
        final Long currentEpoch = Instant.now().toEpochMilli();
        return UserDetailsResponse.builder()
                .success(Boolean.TRUE)
                .message("User details retrieved successfully...")
                .timestamp(currentEpoch)
                .user(users.getUserDetails())
                .addedBy(users.getAddedBy())
                .unit(users.getUnit())
                .lastUpdatedAt(users.getUpdatedAt())
                .orgName(users.getOrgName())
                .build();
    }
}
