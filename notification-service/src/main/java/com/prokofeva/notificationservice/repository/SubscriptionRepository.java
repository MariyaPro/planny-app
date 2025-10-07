package com.prokofeva.notificationservice.repository;

import com.prokofeva.notificationservice.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    List<Subscription> getSubscriptionByReportType_NameAndActive(String reportTypeName, boolean isActive);

    List<Subscription> getSubscriptionByReportType_NameAndActiveAndRecipient_NameInIgnoreCase(String reportTypeName, boolean isActive, List<String> recipientNames);
}
