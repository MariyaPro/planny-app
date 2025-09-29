package com.prokofeva.notificationservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record EventDto(
        @NotEmpty
        String title,
        @NotEmpty
        String ownerName,
        @NotEmpty
        String eventTypeName,
        @NotNull
        LocalDate dateStart,
        LocalDate dateEnd,
        LocalTime startTime,
        LocalTime endTime,
        String description,
        String location
) {
}
