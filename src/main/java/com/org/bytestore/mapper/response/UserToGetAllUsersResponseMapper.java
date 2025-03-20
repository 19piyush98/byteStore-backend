package com.org.bytestore.mapper.response;

import com.org.bytestore.model.entity.User;
import com.org.bytestore.model.response.GetAllUsersResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserToGetAllUsersResponseMapper {

    public GetAllUsersResponse map(final List<User> users){
        return GetAllUsersResponse.builder()
                .success(Boolean.TRUE)
                .message("Getting All Users Successfully...")
                .users(users)
                .build();
    }
}
