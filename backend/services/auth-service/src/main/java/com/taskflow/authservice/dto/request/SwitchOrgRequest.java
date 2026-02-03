package com.taskflow.authservice.dto.request;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

public record SwitchOrgRequest(
        @NotNull(message = "Organization ID is required") UUID orgId
) implements Serializable {
}
