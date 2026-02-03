package com.taskflow.authservice.dto.response;

import java.io.Serializable;
import java.util.UUID;

public record UserOrgDto(
        UUID orgId,
        String orgName,
        String orgSlug,
        String userRole,
        String orgUrl
) implements Serializable {
}
