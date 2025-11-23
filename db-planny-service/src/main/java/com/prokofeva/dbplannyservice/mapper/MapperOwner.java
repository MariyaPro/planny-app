package com.prokofeva.dbplannyservice.mapper;

import com.prokofeva.dbplannyservice.dto.OwnerDto;
import com.prokofeva.dbplannyservice.entity.Owner;

public class MapperOwner {

    public static Owner toEntity(OwnerDto dto) {
        return Owner.builder()
                .id(dto.id())
                .name(dto.name())
                .userIdTg(dto.userIdTg())
                .description(dto.description())
                .active(true)
                .build();
    }

    public static OwnerDto toDto(Owner entity) {
        return OwnerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .userIdTg(entity.getUserIdTg())
                .description(entity.getDescription())
                .active(entity.isActive())
                .build();
    }
}
