package com.prokofeva.dbplannyservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

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
    @NotEmpty
    private String title;
    @NotEmpty
    private String ownerName;
    @NotEmpty
    private String eventTypeName;
    @NotNull
    private LocalDate dateEvent;
    private LocalTime startTime;
    private LocalTime endTime;

    private String description;
    private String location;

    @Value(value = "true")
    private boolean active;
    private LocalDateTime updated;

}
