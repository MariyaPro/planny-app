package com.prokofeva.notificationservice.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record NotificationRequest(
        @NotNull
        String reportTypeCode,
        LocalDate date,
        List<String> recipients,
        List<String> owners,
        String eventType
) {
}