package com.prokofeva.tgbotplanny.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ReportRequest(
        Long userIdTg,
        LocalDate dateStart,
        LocalDate dateEnd,
        List<String> owners,
        String eventType
) {
}
