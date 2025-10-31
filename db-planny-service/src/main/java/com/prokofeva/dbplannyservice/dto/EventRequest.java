package com.prokofeva.dbplannyservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
public record EventRequest(
        @NotEmpty
        List<EventDto> eventDtos) {
}
