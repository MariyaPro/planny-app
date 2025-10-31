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
                .ownerId(owner.id())
                .eventType(eventType)
                .dateEvent(dto.getDateEvent())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .description(dto.getDescription().isEmpty() ? null : dto.getDescription())
                .location(dto.getLocation().isEmpty() ? null : dto.getLocation())
                .active(Objects.isNull(dto.getActive()) || dto.getActive())
                .build();
    }

    public EventDto toDto(Event entity) {
        var ownerName = ownerService.findById(entity.getOwnerId()).name();
        return EventDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .ownerName(ownerName)
                .eventTypeName(entity.getEventType() == null ? null : entity.getEventType().getName())
                .dateEvent(entity.getDateEvent())
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
