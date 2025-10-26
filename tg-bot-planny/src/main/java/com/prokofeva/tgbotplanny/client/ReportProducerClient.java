package com.prokofeva.tgbotplanny.client;

import com.prokofeva.tgbotplanny.dto.ReportRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "reportProducerService",
        url = "${services.report-generator}"
)
public interface ReportProducerClient {
    @PostMapping("/generate-json")
    String generateReportJson(@RequestBody ReportRequest requestBody);

    @PostMapping("/generate-txt")
    String generateReportTxt(@RequestBody ReportRequest requestBody);
}
