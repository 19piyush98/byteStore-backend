package com.org.bytestore.model.response;

import com.org.bytestore.model.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GetAllUsersResponse {

    private Boolean success;

    private String message;

    private List<User> users;
}
