package com.taskflow.authservice.mapper;

import com.taskflow.authservice.dto.response.UserDto;
import com.taskflow.authservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getAvatarUrl(),
                user.getSystemRole(),
                user.isActive(),
                user.isEmailVerified(),
                user.getLastLoginAt(),
                user.getCurrentOrgId(),
                user.getLocale(),
                user.getCreatedAt(),
                user.getTimezone()
        );
    }
}
