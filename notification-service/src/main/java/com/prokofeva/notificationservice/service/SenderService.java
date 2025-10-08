package com.prokofeva.notificationservice.service;

import com.prokofeva.notificationservice.dto.RecipientConfig;
import com.prokofeva.notificationservice.report.Report;

public interface SenderService {
    void sendMessage(Report report, RecipientConfig config);
}
