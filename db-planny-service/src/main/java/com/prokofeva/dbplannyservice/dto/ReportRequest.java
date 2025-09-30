package com.prokofeva.dbplannyservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ReportRequest(
        @NotNull
        LocalDate dateStart,
        @NotNull
        LocalDate dateEnd,
        List<String> owners,
        String eventType
) {
}
