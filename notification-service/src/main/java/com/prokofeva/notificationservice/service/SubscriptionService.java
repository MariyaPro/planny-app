package com.prokofeva.notificationservice.service;

import com.prokofeva.notificationservice.entity.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getSubscriptionByReportTypeNameAndIsActive(String reportTypeName);

    List<Subscription> getSubscriptionByReportTypeNameAndIsActiveAndRecipientNames(String reportTypeName, List<String> recipientNames);
}
