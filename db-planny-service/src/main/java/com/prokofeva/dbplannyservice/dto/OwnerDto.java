package com.prokofeva.dbplannyservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record OwnerDto(
        UUID id,
        String name,
        String description,
        boolean active,
        LocalDateTime updated) {

}
