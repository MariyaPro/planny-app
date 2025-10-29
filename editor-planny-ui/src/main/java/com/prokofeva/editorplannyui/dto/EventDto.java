package com.prokofeva.editorplannyui.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class EventDto {

    private UUID id;
    private String title;
    private String ownerName;
    private String eventTypeName;
    private LocalDate dateEvent;
    private LocalDate endEvent;
    private LocalTime startTime;
    private LocalTime endTime;

    private String description;
    private String location;
    private String recurrence;

    private Boolean active;
    private LocalDateTime updated;

}
