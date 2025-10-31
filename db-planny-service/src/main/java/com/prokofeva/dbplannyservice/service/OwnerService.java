package com.prokofeva.dbplannyservice.service;

import com.prokofeva.dbplannyservice.dto.OwnerDto;

import java.util.List;
import java.util.UUID;

public interface OwnerService {
    OwnerDto findOwnerByName(String name);

    List<OwnerDto> findAllActive();

    OwnerDto findById(UUID ownerId);
}
