package com.prokofeva.editorplannyui.dto;

import com.prokofeva.editorplannyui.enums.Recurrence;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record EventForm(
        String title,
        String ownerId,
        String eventTypeName,
        LocalDate startEvent,
        LocalDate endEvent,
        LocalTime startTime,
        LocalTime endTime,
        String description,
        String location,
        Recurrence recurrence
) {

}
