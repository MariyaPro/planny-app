package com.prokofeva.notificationservice.service;

import com.prokofeva.notificationservice.dto.RecipientConfig;

import java.util.UUID;

public interface SenderService {
    void sendMessage(UUID reportId, RecipientConfig config);
}
