package com.taskflow.authservice.dto.response;

import java.io.Serializable;
import java.util.UUID;

public record UserOrgDto(
        UUID orgId,
        String orgName,
        String orgSlug,
        String orgRole,
        String orgUrl
) implements Serializable {
}
