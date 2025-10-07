package com.prokofeva.notificationservice.service.impl;

import com.prokofeva.notificationservice.dto.RecipientConfig;
import com.prokofeva.notificationservice.service.NotificationService;
import com.prokofeva.notificationservice.service.RecipientConfigService;
import com.prokofeva.notificationservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final SubscriptionService subscriptionService;
    private final RecipientConfigService recipientConfigService;


    @Override
    public Map<String, List<RecipientConfig>> getDeliveryMethodConfigs(String reportTypeName, List<String> recipients) {
        var subscriptions = Objects.isNull(recipients) || recipients.isEmpty()
                ? subscriptionService.getSubscriptionByReportTypeNameAndIsActive(reportTypeName)
                : subscriptionService.getSubscriptionByReportTypeNameAndIsActiveAndRecipientNames(reportTypeName, recipients);
        Map<String, List<RecipientConfig>> recipientConfigMap = new HashMap<>();
        subscriptions.forEach(subscription -> {
            var codeConfig = subscription.getRecipient().getCodeConfig();
            var config = recipientConfigService.getRecipientConfig(codeConfig);
            var deliveryMethod = config.deliveryMethod();
            recipientConfigMap.computeIfAbsent(deliveryMethod, k -> new ArrayList<>()).add(config);
        });
        return recipientConfigMap;
    }
}
