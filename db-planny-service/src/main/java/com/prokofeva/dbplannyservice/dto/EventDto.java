package com.prokofeva.dbplannyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EventDto {

    private UUID id;
    private String name;
    private String category;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String description;
}
