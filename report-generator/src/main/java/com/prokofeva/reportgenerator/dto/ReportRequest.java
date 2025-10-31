package com.prokofeva.reportgenerator.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ReportRequest(
        LocalDate dateStart,
        LocalDate dateEnd,
        List<String> owners,
        String eventType
) {
}
