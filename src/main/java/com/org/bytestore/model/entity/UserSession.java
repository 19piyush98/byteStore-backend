package com.org.bytestore.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document("UserSession")
@Data
public class UserSession {

    @Id
    private String userId;

    private String sessionToken;

    private Long expirationEpoch;
}

