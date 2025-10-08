package com.prokofeva.notificationservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prokofeva.notificationservice.dto.RecipientConfig;
import com.prokofeva.notificationservice.exceptions.LogException;
import com.prokofeva.notificationservice.service.RecipientConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipientConfigServiceImpl implements RecipientConfigService {
    @Value("${recipient.config.path}")
    private String pathLoad;

    @Override
    public RecipientConfig getRecipientConfig(String codeConfig) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(pathLoad + codeConfig + ".json"), RecipientConfig.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LogException(String.format("Failed to load recipient config (codeConfig: {%s})", codeConfig));
        }
    }
}
