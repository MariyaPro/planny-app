package com.prokofeva.notificationservice.service;

import com.prokofeva.notificationservice.dto.RecipientConfig;
import com.prokofeva.notificationservice.report.Report;

import java.util.List;
import java.util.Map;

public interface NotificationService {
    Map<String, List<RecipientConfig>> getDeliveryMethodConfigs(String reportTypeName, List<String> recipients);

    void sendReport(Report report, Map<String, List<RecipientConfig>> mapDeliveryMethodConfig);
}
