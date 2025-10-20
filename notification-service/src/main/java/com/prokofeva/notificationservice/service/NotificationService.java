package com.prokofeva.notificationservice.service;

import com.prokofeva.notificationservice.dto.RecipientConfig;
import com.prokofeva.notificationservice.enums.MessageFormat;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface NotificationService {
    Map<String, List<RecipientConfig>> getDeliveryMethodConfigs(String reportTypeName, List<String> recipients, Set<String> msgFormats);

    void sendReport(Map<MessageFormat, UUID> report, Map<String, List<RecipientConfig>> mapDeliveryMethodConfig);
}
