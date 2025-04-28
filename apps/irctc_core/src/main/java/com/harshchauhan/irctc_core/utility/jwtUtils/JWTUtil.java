package com.harshchauhan.irctc_core.utility.jwtUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.harshchauhan.irctc_core.modules.userModule.core.UserAuthDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JWTUtil {

    private static final String SECRET_KEY = "your-secure-secret-key-min-32bytes";
    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public String generateToken(UserAuthDetails userAuthDetails, long expiryMinutes) {
        return Jwts.builder()
                .subject(userAuthDetails.getUsername())
                .claims(Map.of("id", userAuthDetails.getId(), "name", userAuthDetails.getName()))
                .header().empty().add("typ", "JWT")
                .and()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiryMinutes * 60 * 1000))
                .signWith(key).compact();
    }

    public UserAuthDetails validateAndExtractData(String token) {
        try {
            Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();

            UserAuthDetails userAuthDetails = new UserAuthDetails();
            userAuthDetails.setId(claims.get("id", Long.class));
            userAuthDetails.setName(claims.get("name", String.class));
            userAuthDetails.setUsername(claims.getSubject());
            return userAuthDetails;
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    public String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
