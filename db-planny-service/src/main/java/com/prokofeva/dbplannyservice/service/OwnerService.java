package com.prokofeva.dbplannyservice.service;

import com.prokofeva.dbplannyservice.dto.OwnerDto;

import java.util.List;
import java.util.UUID;

public interface OwnerService {
    List<OwnerDto> findAllActive(boolean isActive);

    OwnerDto findById(UUID ownerId);

    UUID save(OwnerDto ownerDto);

    OwnerDto findOwnerByUserIdTg(Long userIdTg);

    void updateOwner(OwnerDto ownerUpdated);
}
