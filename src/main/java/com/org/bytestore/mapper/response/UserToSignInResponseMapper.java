package com.org.bytestore.mapper.response;

import com.org.bytestore.component.Authentication;
import com.org.bytestore.model.entity.User;
import com.org.bytestore.model.response.SignInResponse;
import com.org.bytestore.model.response.SignUpResponse;
import com.org.bytestore.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class UserToSignInResponseMapper {

    private final Authentication authentication;
    public SignInResponse map(final User user){
        final String sessionToken = TokenUtils.generateNewToken();
        final Long expirationEpoch  = Instant.now().toEpochMilli() + 60*60*1000;
        authentication.addUserSession(user.getUserId(), sessionToken,expirationEpoch);
        return SignInResponse.builder()
                .userId(user.getUserId())
                .isValid(Boolean.TRUE)
                .expirationEpoch(expirationEpoch)
                .sessionToken(sessionToken)
                .orgTokenId(user.getOrgTokenId())
                .build();
    }
}
