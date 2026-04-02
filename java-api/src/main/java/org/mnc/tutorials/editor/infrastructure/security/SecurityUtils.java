package org.mnc.tutorials.editor.infrastructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public final class SecurityUtils {

    private SecurityUtils() {}

    /**
     * Extracts the Keycloak Subject (sub) as a UUID.
     */
    public static UUID getUserId() {
        Jwt jwt = getJwt();
        return jwt != null ? UUID.fromString(jwt.getSubject()) : null;
    }

    /**
     * Extracts the login (username) from the JWT.
     * In Keycloak, this is usually the "preferred_username" claim.
     */
    public static String getUserLogin() {
        Jwt jwt = getJwt();
        return jwt != null ? jwt.getClaimAsString("preferred_username") : "anonymous";
    }

    /**
     * Helper to get the current JWT from the Security Context
     */
    private static Jwt getJwt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt;
        }
        return null;
    }
}
