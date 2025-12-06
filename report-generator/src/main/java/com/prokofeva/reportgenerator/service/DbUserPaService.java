package com.prokofeva.reportgenerator.service;

import com.prokofeva.reportgenerator.dto.OwnerDto;

import java.util.List;

public interface DbUserPaService {
    List<OwnerDto> getUserPASubscriptions(Long userIdTg);
}
