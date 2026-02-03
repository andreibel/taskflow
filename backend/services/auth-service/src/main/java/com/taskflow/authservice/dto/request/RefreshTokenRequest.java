package com.taskflow.authservice.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.taskflow.authservice.entity.RefreshToken}
 */
public record RefreshTokenRequest(
        @NotBlank(message = "Refresh token is required") String refreshToken) implements Serializable {
}