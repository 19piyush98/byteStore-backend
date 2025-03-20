package com.org.bytestore.model.entity;

import com.org.bytestore.model.request.UserDetails;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document("User")
@Data
public class User {

    @Id
    private String userId;

    private String orgName;

    private String orgTokenId;

    private String unit;

    private String email;

    private String password;

    private long createdAt;

    private long updatedAt;

    private UserDetails userDetails;

    private String addedBy;
}
