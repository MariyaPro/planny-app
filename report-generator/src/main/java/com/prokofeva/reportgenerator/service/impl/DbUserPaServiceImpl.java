package com.prokofeva.reportgenerator.service.impl;

import com.prokofeva.reportgenerator.client.DbUserPaClient;
import com.prokofeva.reportgenerator.dto.OwnerDto;
import com.prokofeva.reportgenerator.service.DbUserPaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbUserPaServiceImpl implements DbUserPaService {
    private final DbUserPaClient dbUserPaClient;

    @Override
    public List<OwnerDto> getUserPASubscriptions(Long userIdTg) {
        return dbUserPaClient.getUserPASubscriptions(userIdTg);
    }
}
