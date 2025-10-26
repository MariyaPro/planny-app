package com.prokofeva.notificationservice.facade;

import com.prokofeva.notificationservice.client.ReportGeneratorClient;
import com.prokofeva.notificationservice.dto.NotificationRequest;
import com.prokofeva.notificationservice.dto.ReportRequest;
import com.prokofeva.notificationservice.enums.MessageFormat;
import com.prokofeva.notificationservice.enums.ReportTypeCode;
import com.prokofeva.notificationservice.exceptions.ProcessFailException;
import com.prokofeva.notificationservice.exceptions.ProcessStopException;
import com.prokofeva.notificationservice.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationFacade {
    private final NotificationService notificationService;
    private final ReportGeneratorClient reportGeneratorClient;

    public void sendNotification(@Valid NotificationRequest request) {
        try {
            var reportTypeCode = Objects.requireNonNull(ReportTypeCode.getReportTypeByName(request.periodName()));
            Set<String> msgFormats = new HashSet<>();
            var mapDeliveryMethodConfig =
                    notificationService.getDeliveryMethodConfigs(reportTypeCode.getName(), request.recipients(), msgFormats);
            var reportRequest = buildRequestByType(reportTypeCode, request);
            Map<MessageFormat, UUID> mapReports = new HashMap<>();
            msgFormats.forEach(msgFormat -> {
                try {
                    MessageFormat format = MessageFormat.valueOf(msgFormat);
                    UUID reportId = switch (format) {
                        case TXT -> reportGeneratorClient.generateReportTxt(reportRequest);
                        case XML -> reportGeneratorClient.generateReportXml(reportRequest);
                        case JSON -> reportGeneratorClient.generateReportJson(reportRequest);
                    };
                    mapReports.put(format, reportId);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            });
            notificationService.sendReport(mapReports, mapDeliveryMethodConfig);
        } catch (ProcessStopException e) {
            log.info(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private ReportRequest buildRequestByType(ReportTypeCode reportTypeCode, NotificationRequest request) {
        try {
            var startDate = switch (reportTypeCode) {
                case FREE_REPORT -> request.date();
                case WEEK_REPORT, MONTH_REPORT, TODAY_REPORT -> LocalDate.now();
                case TOMORROW_REPORT -> LocalDate.now().plusDays(1);
            };
            var endDate = switch (reportTypeCode) {
                case TODAY_REPORT , TOMORROW_REPORT -> startDate;
                case WEEK_REPORT -> startDate.plusDays(6);
                case MONTH_REPORT -> startDate.plusMonths(1);
                case FREE_REPORT -> Objects.isNull(request.endDate()) ? LocalDate.now() : request.endDate();
            };
            return ReportRequest.builder()
                    .dateStart(startDate)
                    .dateEnd(endDate)
                    .owners(Objects.isNull(request.owners()) ? List.of() : request.owners())
                    .eventType(request.eventType())
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ProcessFailException("Failed to build report request. Caused by: {}" + e.getMessage());
        }
    }
}
