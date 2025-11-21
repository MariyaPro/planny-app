package com.prokofeva.dbplannyservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@Builder
public record OwnerDto(
        UUID id,
        String name,
        Long userIdTg,
        String description,
        boolean active,
        LocalDateTime updated) {

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OwnerDto ownerDto = (OwnerDto) o;
        return active == ownerDto.active && Objects.equals(name, ownerDto.name) && Objects.equals(userIdTg, ownerDto.userIdTg) && Objects.equals(description, ownerDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userIdTg, description, active);
    }
}
