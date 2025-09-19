package com.prokofeva.dbplannyservice.mapper;

import com.prokofeva.dbplannyservice.dto.EventDto;
import com.prokofeva.dbplannyservice.entity.Event;
import com.prokofeva.dbplannyservice.enums.Category;

import java.util.Optional;

public class MapperEvent {
    public static Event toEntity(EventDto dto) {
        var entity = new Event();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCategory(Category.valueOf(dto.getCategory()));
        entity.setDateStart(dto.getDateStart());
        entity.setDateEnd(Optional.ofNullable(dto.getDateEnd()).orElse(dto.getDateStart()));
        return entity;
    }

    public static EventDto toDto(Event entity) {
        var dto = new EventDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCategory(entity.getCategory().name());
        dto.setDateStart(entity.getDateStart());
        dto.setDateEnd(entity.getDateEnd());
        return dto;
    }
}
