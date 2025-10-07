package com.prokofeva.notificationservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ReportTypeDto(
        UUID id,
        String name,
        String description,
        boolean active,
        LocalDateTime updated
) {
}
