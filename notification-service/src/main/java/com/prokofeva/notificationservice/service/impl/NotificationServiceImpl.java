package com.prokofeva.notificationservice.service.impl;

import com.prokofeva.notificationservice.dto.RecipientConfig;
import com.prokofeva.notificationservice.enums.MessageFormat;
import com.prokofeva.notificationservice.exceptions.ProcessFailException;
import com.prokofeva.notificationservice.exceptions.ProcessStopException;
import com.prokofeva.notificationservice.service.NotificationService;
import com.prokofeva.notificationservice.service.RecipientConfigService;
import com.prokofeva.notificationservice.service.SubscriptionService;
import com.prokofeva.notificationservice.service.impl.sender.SenderServiceFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final SubscriptionService subscriptionService;
    private final RecipientConfigService recipientConfigService;
    private final SenderServiceFactory senderServiceFactory;

    @Override
    public Map<String, List<RecipientConfig>> getDeliveryMethodConfigs(String reportTypeName, List<String> recipients, Set<String> msgFormats) {
        var subscriptions = Objects.isNull(recipients) || recipients.isEmpty()
                ? subscriptionService.getSubscriptionByReportTypeNameAndIsActive(reportTypeName)
                : subscriptionService.getSubscriptionByReportTypeNameAndIsActiveAndRecipientNames(reportTypeName, recipients);
        log.info("{} out of {} recipients found.", subscriptions.size(), recipients == null ? "all" : recipients.size());
        if (subscriptions.isEmpty()) {
            throw new ProcessStopException("No subscriptions found for report.");
        }
        Map<String, List<RecipientConfig>> recipientConfigMap = new HashMap<>();
        subscriptions.forEach(subscription -> {
            try {
                var codeConfig = subscription.getRecipient().getCodeConfig();
                var config = recipientConfigService.getRecipientConfig(codeConfig);
                msgFormats.add(config.deliveryMethod());
                var deliveryMethod = config.deliveryMethod();
                recipientConfigMap.computeIfAbsent(deliveryMethod, k -> new ArrayList<>()).add(config);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
        log.info("{} out of {} recipient configs found.", recipientConfigMap.size(), subscriptions.size());
        if (recipientConfigMap.isEmpty()) {
            throw new ProcessFailException("No corresponding recipient configs found for report.");
        }
        return recipientConfigMap;
    }

    @Override
    public void sendReport(Map<MessageFormat, UUID> reports, Map<String, List<RecipientConfig>> mapDeliveryMethodConfig) {
        AtomicInteger totalCount = new AtomicInteger();
        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();
        mapDeliveryMethodConfig.forEach((deliveryMethod, configs) -> {
            var sender = senderServiceFactory.getSender(deliveryMethod);
            configs.forEach(c -> {
                try {
                    totalCount.getAndIncrement();
                    var format = c.messageFormat();
                    sender.sendMessage(reports.get(format), c);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    log.error(e.getMessage());
                    failCount.incrementAndGet();
                }
            });
        });
        log.info("The process completed with the result: totalCount={}, successCount={}, failCount={}.",
                totalCount.get(), successCount.get(), failCount.get());
    }
}
