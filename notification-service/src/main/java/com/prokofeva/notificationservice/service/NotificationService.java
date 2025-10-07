package com.prokofeva.notificationservice.service;

import com.prokofeva.notificationservice.dto.RecipientConfig;

import java.util.List;
import java.util.Map;

public interface NotificationService {
    Map<String, List<RecipientConfig>> getDeliveryMethodConfigs(String reportTypeName, List<String> recipients);
}
