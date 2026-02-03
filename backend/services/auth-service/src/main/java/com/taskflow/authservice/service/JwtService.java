package com.taskflow.authservice.service;

import com.taskflow.authservice.config.JwtProperties;
import com.taskflow.authservice.dto.response.UserOrgDto;
import com.taskflow.authservice.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;


@Service
public class JwtService {

    private final SecretKey secretKey;
    private final long accessTokenExpiration;

    public JwtService(JwtProperties jwtProperties) {
        this.secretKey = Keys.hmacShaKeyFor(
                jwtProperties.secret().getBytes(StandardCharsets.UTF_8)
        );
        this.accessTokenExpiration = jwtProperties.accessTokenExpiration();
    }
    /**
     * Generate access token with org context (after login/switch-org)
     */
    public String generateAccessToken(User user, UserOrgDto userOrgDto) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + accessTokenExpiration);

        var builder = Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("systemRole", user.getSystemRole())
                .issuedAt(now)
                .expiration(expiration);

        if (userOrgDto != null) {
            builder.claim("orgId", userOrgDto.orgId())
                    .claim("orgRole", userOrgDto.orgRole())
                    .claim("orgSlug", userOrgDto.orgSlug());
        }
        return builder.signWith(secretKey).compact();
    }
    /**
     * Generate access token without org context (new user, no org yet)
     */
    public String generateAccessToken(User user) {
        return generateAccessToken(user, null);
    }
    /**
     * Validate token and extract claims
     */
    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }
    /**
     * Extract user ID from token
     */
    public UUID extractUserId(String token) {
        Claims claims = parseToken(token);
        return UUID.fromString(claims.getSubject());
    }
    /**
     * Check if token is valid (signature + not expired)
     */
    public boolean isTokenValid(String token) {
        try {
            parseToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

}
