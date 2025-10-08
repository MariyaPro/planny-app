package com.prokofeva.notificationservice.service.impl.sender;

import com.prokofeva.notificationservice.dto.RecipientConfig;
import com.prokofeva.notificationservice.exceptions.LogException;
import com.prokofeva.notificationservice.report.Report;
import com.prokofeva.notificationservice.service.SenderService;
import com.prokofeva.notificationservice.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SenderEmailServiceImpl implements SenderService {
    @Override
    public void sendMessage(Report report, RecipientConfig config) {
        try {
            log.info("Message for {} sent.\n" +
                     "Contents: {}", config.email(), Util.toJson(report));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LogException("Fail to send message for " + config.email());
        }
    }
}
