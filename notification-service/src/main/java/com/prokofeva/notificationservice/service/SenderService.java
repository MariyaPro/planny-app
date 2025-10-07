package com.prokofeva.notificationservice.service;

import com.prokofeva.notificationservice.dto.RecipientConfig;
import com.prokofeva.notificationservice.report.Report;

import java.util.List;

public interface SenderService {
    void sendMessage(Report report, List<RecipientConfig> configs);
}
