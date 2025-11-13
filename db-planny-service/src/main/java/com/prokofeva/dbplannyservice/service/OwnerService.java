package com.prokofeva.dbplannyservice.service;

import com.prokofeva.dbplannyservice.dto.OwnerDto;
import com.prokofeva.dbplannyservice.entity.Owner;

import java.util.List;
import java.util.UUID;

public interface OwnerService {
    OwnerDto findOwnerByName(String name);

    List<OwnerDto> findAllActive(boolean isActive);

    OwnerDto findById(UUID ownerId);

    UUID save(OwnerDto ownerDto);
}
