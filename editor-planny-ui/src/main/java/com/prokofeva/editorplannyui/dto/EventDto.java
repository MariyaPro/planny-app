package com.prokofeva.editorplannyui.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record EventDto(
        String title,
        String ownerName,
        String eventTypeName,
        LocalDate dateEvent,
        LocalTime startTime,
        LocalTime endTime,
        String description,
        String location,
        Boolean active) {

}
