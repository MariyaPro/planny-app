package com.prokofeva.notificationservice.service.impl.sender;

import com.prokofeva.notificationservice.dto.RecipientConfig;
import com.prokofeva.notificationservice.report.Report;
import com.prokofeva.notificationservice.service.SenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SenderKafkaServiceImpl implements SenderService {
    @Override
    public void sendMessage(Report report, List<RecipientConfig> configs) {

    }
}
