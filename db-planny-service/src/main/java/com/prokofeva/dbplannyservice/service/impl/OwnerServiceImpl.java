package com.prokofeva.dbplannyservice.service.impl;

import com.prokofeva.dbplannyservice.dto.OwnerDto;
import com.prokofeva.dbplannyservice.mapper.MapperOwner;
import com.prokofeva.dbplannyservice.repository.OwnerRepository;
import com.prokofeva.dbplannyservice.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final MapperOwner mapperOwner;

    @Override
    public OwnerDto findOwnerByName(String name) {
        return mapperOwner.toDto(ownerRepository.findOwnerByName(name));
    }

    @Override
    public List<OwnerDto> findAllActive() {
        return ownerRepository.findAllByActive(true).stream()
                .map(mapperOwner::toDto)
                .toList();
    }

    @Override
    public OwnerDto findById(UUID ownerId) {
        return mapperOwner.toDto(ownerRepository.findById(ownerId).get());
    }
}
