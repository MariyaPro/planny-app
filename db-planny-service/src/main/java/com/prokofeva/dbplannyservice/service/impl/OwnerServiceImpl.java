package com.prokofeva.dbplannyservice.service.impl;

import com.prokofeva.dbplannyservice.dto.OwnerDto;
import com.prokofeva.dbplannyservice.mapper.MapperOwner;
import com.prokofeva.dbplannyservice.repository.OwnerRepository;
import com.prokofeva.dbplannyservice.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.prokofeva.dbplannyservice.mapper.MapperOwner.toDto;
import static com.prokofeva.dbplannyservice.mapper.MapperOwner.toEntity;

@RequiredArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Override
    public List<OwnerDto> findAllActive(boolean isActive) {
        return ownerRepository.findAllByActive(isActive).stream()
                .map(MapperOwner::toDto)
                .toList();
    }

    @Override
    public OwnerDto findById(UUID ownerId) {
        return toDto(ownerRepository.findById(ownerId).get());
    }

    @Override
    public UUID save(OwnerDto ownerDto) {
        return ownerRepository.save(toEntity(ownerDto)).getId();
    }

    @Override
    public OwnerDto findOwnerByUserIdTg(Long userIdTg) {
        var ownerDb = ownerRepository.findByUserIdTg(userIdTg);
        return ownerDb == null ? null : toDto(ownerDb);
    }

    @Override
    public void updateOwner(OwnerDto ownerUpdated) {
        ownerRepository.save(toEntity(ownerUpdated));
    }
}
