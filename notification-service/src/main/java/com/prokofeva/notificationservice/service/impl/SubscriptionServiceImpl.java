package com.prokofeva.notificationservice.service.impl;

import com.prokofeva.notificationservice.entity.Subscription;
import com.prokofeva.notificationservice.repository.SubscriptionRepository;
import com.prokofeva.notificationservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public List<Subscription> getSubscriptionByReportTypeNameAndIsActive(String reportTypeName) {
        return subscriptionRepository.getSubscriptionByReportType_NameAndActive(reportTypeName, true);
    }

    @Override
    public List<Subscription> getSubscriptionByReportTypeNameAndIsActiveAndRecipientNames(String reportTypeName, List<String> recipientNames) {
        return subscriptionRepository.getSubscriptionByReportType_NameAndActiveAndRecipient_NameInIgnoreCase(reportTypeName, true,recipientNames);
    }
}
