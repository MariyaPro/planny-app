package com.prokofeva.editorplannyui.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record EventRequest(
        List<EventDto> eventDtos) {
}
