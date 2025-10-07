package com.prokofeva.notificationservice.service;

import com.prokofeva.notificationservice.dto.NotificationRequest;
import com.prokofeva.notificationservice.report.Report;
import jakarta.validation.Valid;

public interface ReportGenerator {

    Report process(@Valid NotificationRequest request);

}
