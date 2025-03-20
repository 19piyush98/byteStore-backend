package com.org.bytestore.mapper.response;

import com.org.bytestore.model.entity.User;
import com.org.bytestore.model.response.AddUserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserToAddUserResponseMapper {

    public AddUserResponse map(final User user){
        return AddUserResponse.builder()
                .userId(user.getUserId())
                .createdAt(user.getCreatedAt())
                .orgTokenId(user.getOrgTokenId())
                .success(Boolean.TRUE)
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
