package com.org.bytestore.mapper.request;

import com.org.bytestore.model.entity.User;
import com.org.bytestore.model.request.UpdateUserInfoRequest;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UpdateUserInfoRequestToUserMapper {

    public User map(@NonNull final UpdateUserInfoRequest updateUserInfoRequest, final User user){
        final Long currentEpoch = Instant.now().toEpochMilli();
        user.setUserDetails(updateUserInfoRequest.getUserDetails());
        user.setUpdatedAt(currentEpoch);
        return user;
    }
}
