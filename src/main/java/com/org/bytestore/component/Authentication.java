package com.org.bytestore.component;

import com.org.bytestore.model.entity.User;
import com.org.bytestore.model.entity.UserSession;
import com.org.bytestore.repository.UserRepository;
import com.org.bytestore.repository.UserSessionRepository;
import com.org.bytestore.utils.GsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
/**
 * This class handles user authentication functionalities in the ByteStore application.
 * It provides methods to add user sessions and verify user authorization based on session tokens.
 */

public class Authentication {

    private final UserSessionRepository userSessionRepository;

    private final UserRepository userRepository;

    public void addUserSession(final String userId, final String sessionToken, final Long expirationEpoch) {
        UserSession userSession = UserSession.builder()
                .userId(userId)
                .sessionToken(sessionToken)
                .expirationEpoch(expirationEpoch)
                .build();
        log.info("Adding user session {}", GsonUtils.toJson(userSession));
        userSessionRepository.save(userSession);
    }

    public Boolean isAuthorised(final String userId, final String sessionToken) {
        log.info("Validating request with userId {}, session Token {}", userId, sessionToken);
        Optional<UserSession> userSession = userSessionRepository.findById(userId);
        final Long currentEpoch = Instant.now().toEpochMilli();
        if (userSession.isPresent() && userSession.get().getSessionToken().equals(sessionToken)
                && currentEpoch < userSession.get().getExpirationEpoch()) {
            return Boolean.TRUE;
        }
        log.info("Returning false");
        return Boolean.FALSE;
    }

    public Boolean isOrganised(final String orgTokenId) {
        log.info("Validating request with orgToken Token {}", orgTokenId);
        Optional<List<User>> user = userRepository.findAllByOrgTokenId(orgTokenId);
        if (user.isPresent()) {
            return Boolean.TRUE;
        }
        log.info("Returning false");
        return Boolean.FALSE;
    }
}


