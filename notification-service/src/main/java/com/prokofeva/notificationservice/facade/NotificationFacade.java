package com.prokofeva.notificationservice.facade;

import com.prokofeva.notificationservice.dto.NotificationRequest;
import com.prokofeva.notificationservice.enums.ReportTypeCode;
import com.prokofeva.notificationservice.service.DbEventsService;
import com.prokofeva.notificationservice.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationFacade {
    private NotificationService notificationService;
    private final DbEventsService dbEventsService;
    private final ReportGeneratorFactory reportGeneratorFactory;

    public void sendNotification(@Valid NotificationRequest request) {
        var reportTypeCode = Objects.requireNonNull(ReportTypeCode.getReportTypeByName(request.reportTypeCode()));
        var reportGenerator = reportGeneratorFactory.getReportGenerator(reportTypeCode);
        var report = reportGenerator.process(request).subscribe();

    }
}
