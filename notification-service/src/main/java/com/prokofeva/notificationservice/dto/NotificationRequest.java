package com.prokofeva.notificationservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Builder
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