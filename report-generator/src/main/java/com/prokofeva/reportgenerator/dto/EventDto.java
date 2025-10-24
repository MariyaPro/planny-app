package com.prokofeva.reportgenerator.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EventDto(
        @NotEmpty
        String title,
        @NotEmpty
        String ownerName,
        @NotEmpty
        String eventTypeName,
        @NotNull
        LocalDate dateEvent,
        LocalTime startTime,
        LocalTime endTime,
        String description,
        String location
) {
}
