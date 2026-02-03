package com.taskflow.authservice.dto.response;

import java.util.List;

/**
 * DTO for {@link com.taskflow.authservice.entity.RefreshToken}
 */
public record AuthResponse(
        String accessToken,
        String refreshToken,
        UserDto user,
        List<UserOrgDto> organizations) {
}