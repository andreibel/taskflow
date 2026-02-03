package com.taskflow.authservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.taskflow.authservice.entity.User}
 */
public record RegisterRequest(
        @Email(message = "Invalid email format") @NotBlank(message = "Email is required") String email,
        @Size(message = "Password must be between 8 and 100 characters", min = 8, max = 100) @NotBlank(message = "Password is required") String password,
        @Size(message = "First name must not exceed 100 characters", max = 100) @NotBlank(message = "First name is required") String firstName,
        @Size(message = "Last name must not exceed 100 characters", max = 100) @NotBlank(message = "Last name is required") String lastName) implements Serializable {
}