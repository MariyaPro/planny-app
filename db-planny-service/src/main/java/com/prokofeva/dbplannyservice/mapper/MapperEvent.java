package com.prokofeva.dbplannyservice.mapper;

import com.prokofeva.dbplannyservice.dto.EventDto;
import com.prokofeva.dbplannyservice.entity.Event;
import com.prokofeva.dbplannyservice.service.EventTypeService;
import com.prokofeva.dbplannyservice.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MapperEvent {
    private final EventTypeService eventTypeService;
    private final OwnerService ownerService;

    public Event toEntity(EventDto dto) {
        var owner = ownerService.findOwnerByName(dto.getOwnerName());
        var eventType = eventTypeService.findEventTypeByName(dto.getEventTypeName());

        return Event.builder()
                .title(dto.getTitle())
                .owner(owner)
                .eventType(eventType)
                .dateStart(dto.getDateStart())
                .dateEnd(dto.getDateEnd())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .active(Objects.isNull(dto.isActive()) || dto.isActive())
                .build();
    }

    public EventDto toDto(Event entity) {
        return EventDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .ownerName(entity.getOwner().getName())
                .eventTypeName(entity.getEventType().getName())
                .dateStart(entity.getDateStart())
                .dateEnd(entity.getDateEnd())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .description(entity.getDescription())
                .location(entity.getLocation())
                .description(entity.getDescription())
                .active(entity.isActive())
                .updated(entity.getUpdated())
                .build();
    }
}
