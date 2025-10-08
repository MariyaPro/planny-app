package com.prokofeva.notificationservice.facade;

import com.prokofeva.notificationservice.dto.NotificationRequest;
import com.prokofeva.notificationservice.enums.ReportTypeCode;
import com.prokofeva.notificationservice.exceptions.ProcessStopException;
import com.prokofeva.notificationservice.service.DbEventsService;
import com.prokofeva.notificationservice.service.NotificationService;
import com.prokofeva.notificationservice.service.ReportGenerator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationFacade {
    private final NotificationService notificationService;
    private final ReportGenerator reportGenerator;
    private final DbEventsService dbEventsService;

    public void sendNotification(@Valid NotificationRequest request) {
        try {
            var reportTypeCode = Objects.requireNonNull(ReportTypeCode.getReportTypeByName(request.periodName()));
            var requestToDb = reportGenerator.buildRequestByType(reportTypeCode, request);
            var dataFromDb = dbEventsService.getDataForReport(requestToDb);
            if (dataFromDb == null || dataFromDb.isEmpty()) {
                throw new ProcessStopException("No data found for period.");
            }
            var report = reportGenerator.generateReport(requestToDb, dataFromDb);
            //todo save to Mongo
            var mapDeliveryMethodConfig =
                    notificationService.getDeliveryMethodConfigs(reportTypeCode.getName(), request.recipients());
            notificationService.sendReport(report, mapDeliveryMethodConfig);
        } catch (ProcessStopException e) {
            log.info(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
