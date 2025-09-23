package com.prokofeva.notificationservice.dto;

import com.prokofeva.notificationservice.enums.ReportTypeCode;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record NotificationRequest(
        @NotNull
        ReportTypeCode reportTypeCode,
        LocalDate dateStart,
        LocalDate dateEnd,
        List<String> recipients,
        List<String> owners
) {
}