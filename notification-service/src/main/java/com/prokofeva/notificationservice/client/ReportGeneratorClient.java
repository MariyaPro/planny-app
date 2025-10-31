package com.prokofeva.notificationservice.client;

import com.prokofeva.notificationservice.dto.ReportRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(
        name = "reportGeneratorService",
        url = "${services.report-generator-service}"
)
public interface ReportGeneratorClient {
    @PostMapping("/generate-xml")
    UUID generateReportXml(ReportRequest request);

    @PostMapping("/generate-json")
    UUID generateReportJson(ReportRequest request);

    @PostMapping("/generate-txt")
    UUID generateReportTxt(ReportRequest request);
}
