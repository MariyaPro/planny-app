package com.prokofeva.tgbotplanny.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record OwnerDto(
        UUID id,
        String name,
        Long userIdTg,
        String description,
        boolean active,
        LocalDateTime updated) {

}
