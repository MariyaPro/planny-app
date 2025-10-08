package com.prokofeva.notificationservice.dto;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record NotificationRequest(
        @NotEmpty
        String periodName,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate date,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate endDate,
        List<String> recipients,
        List<String> owners,
        String eventType
) {
}