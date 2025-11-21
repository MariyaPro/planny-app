package com.prokofeva.dbplannyservice.facade;

import com.prokofeva.dbplannyservice.dto.OwnerDto;
import com.prokofeva.dbplannyservice.service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OwnerFacade {
    private final OwnerService ownerService;

    public UUID checkAndUpdate(OwnerDto ownerDto) {
        OwnerDto ownerDb;
        if (ownerDto.userIdTg() != null) {
            ownerDb = ownerService.findOwnerByUserIdTg(ownerDto.userIdTg());
            if (ownerDb != null) {
                if (ownerDb.equals(ownerDto))
                    return ownerDb.id();
                var ownerUpdated = OwnerDto.builder()
                        .id(ownerDb.id())
                        .name(ownerDto.name())
                        .userIdTg(ownerDto.userIdTg())
                        .description(ownerDto.description())
                        .active(true)
                        .build();
                ownerService.updateOwner(ownerUpdated);
                return ownerDb.id();
            }
        }
        return ownerService.save(ownerDto);
    }

}
