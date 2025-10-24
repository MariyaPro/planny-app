package com.prokofeva.notificationservice.service.impl.sender;

import com.prokofeva.notificationservice.dto.RecipientConfig;
import com.prokofeva.notificationservice.service.SenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SenderTgServiceImpl implements SenderService {
    @Override
    public void sendMessage(UUID reportId, RecipientConfig config) {

    }
}
