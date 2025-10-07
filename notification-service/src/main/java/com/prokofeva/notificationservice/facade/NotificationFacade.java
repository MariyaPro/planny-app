package com.prokofeva.notificationservice.facade;

import com.prokofeva.notificationservice.dto.NotificationRequest;
import com.prokofeva.notificationservice.dto.RecipientConfig;
import com.prokofeva.notificationservice.enums.ReportTypeCode;
import com.prokofeva.notificationservice.service.NotificationService;
import com.prokofeva.notificationservice.service.ReportTypeService;
import com.prokofeva.notificationservice.service.impl.reportGenerator.ReportGeneratorFactory;
import com.prokofeva.notificationservice.service.impl.sender.SenderServiceFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationFacade {
    private final NotificationService notificationService;
    private final ReportGeneratorFactory reportGeneratorFactory;
    private final ReportTypeService reportTypeService;
    private final SenderServiceFactory senderServiceFactory;

    public void sendNotification(@Valid NotificationRequest request) {
        var reportType = reportTypeService.findByName(request.reportTypeName());
        Map<String, List<RecipientConfig>> mapDeliveryMethodConfig = notificationService.getDeliveryMethodConfigs(reportType.name(), request.recipients());

        var reportTypeCode = Objects.requireNonNull(ReportTypeCode.getReportTypeByName(request.reportTypeName()));
        var reportGenerator = reportGeneratorFactory.getReportGenerator(reportTypeCode);
        var report = reportGenerator.process(request);

        mapDeliveryMethodConfig.forEach((deliveryMethod, configs) -> senderServiceFactory.getSender(deliveryMethod).sendMessage(report, configs));

    }

}
