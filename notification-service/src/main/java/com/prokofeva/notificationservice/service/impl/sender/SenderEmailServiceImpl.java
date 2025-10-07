package com.prokofeva.notificationservice.service.impl.sender;

import com.prokofeva.notificationservice.dto.RecipientConfig;
import com.prokofeva.notificationservice.report.Report;
import com.prokofeva.notificationservice.service.SenderService;
import com.prokofeva.notificationservice.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SenderEmailServiceImpl implements SenderService {
    @Override
    public void sendMessage(Report report, List<RecipientConfig> configs) {
        configs.forEach(recipientConfig ->
                log.info("Message for {} sent.\n" +
                         "Contents: {}", recipientConfig.email(), Util.toJson(report)));
    }
}
