package com.prokofeva.notificationservice.service;

import com.prokofeva.notificationservice.dto.NotificationRequest;
import com.prokofeva.notificationservice.report.Report;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

public interface ReportGenerator {

    Mono<Report> process(@Valid NotificationRequest request);

}
