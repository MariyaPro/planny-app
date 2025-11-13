package com.prokofeva.dbplannyservice.mapper;

import com.prokofeva.dbplannyservice.dto.OwnerDto;
import com.prokofeva.dbplannyservice.entity.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperOwner {

    public Owner toEntity(OwnerDto dto) {
        return Owner.builder()
//                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .active(true)
                .build();
    }

    public OwnerDto toDto(Owner entity) {
        return OwnerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .active(entity.isActive())
                .build();
    }
}
