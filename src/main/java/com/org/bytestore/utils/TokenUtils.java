package com.org.bytestore.utils;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@UtilityClass
public class TokenUtils {
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    public String generateNewToken() {
        byte[] randomBytes = new byte[128];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
