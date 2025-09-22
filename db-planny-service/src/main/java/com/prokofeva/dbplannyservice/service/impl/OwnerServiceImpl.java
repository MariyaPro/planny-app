package com.prokofeva.dbplannyservice.service.impl;

import com.prokofeva.dbplannyservice.entity.Owner;
import com.prokofeva.dbplannyservice.repository.OwnerRepository;
import com.prokofeva.dbplannyservice.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Override
    public Owner findOwnerByName(String name) {
        return ownerRepository.findOwnerByName(name);
    }
}
