package com.taskflow.authservice.dto.response;

/**
 * DTO for {@link com.taskflow.authservice.entity.RefreshToken}
 */
public record TokenResponse(
        String accessToken,
        String refreshToken) {
}