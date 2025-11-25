package com.prokofeva.editorplannyui.service.impl;

import com.prokofeva.editorplannyui.client.DbUserPAClient;
import com.prokofeva.editorplannyui.dto.OwnerDto;
import com.prokofeva.editorplannyui.service.DbUserPAService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DbUserPAServiceImpl implements DbUserPAService {
    private final DbUserPAClient dbUserPAClient;

    @Override
    public List<OwnerDto> getOwnersList(long userIdTg) {
        return dbUserPAClient.getOwnersList(userIdTg);
    }

    @Override
    public List<OwnerDto> getOwnersDemoList() {
        return dbUserPAClient.getOwnersDemoList();
    }

}
