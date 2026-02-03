package com.taskflow.authservice.dto.response;

import com.taskflow.authservice.enums.SystemRole;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link com.taskflow.authservice.entity.User}
 */
public record UserDto(UUID id,
                      String email,
                      String firstName,
                      String lastName,
                      String avatarUrl,
                      SystemRole systemRole,
                      boolean isActive,
                      boolean isEmailVerified,
                      Instant lastLoginAt,
                      UUID currentOrgId,
                      String locale,
                      Instant createdAt,
                      String timezone) implements Serializable {
}