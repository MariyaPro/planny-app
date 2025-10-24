package com.prokofeva.tgbotplanny.commands;

import com.prokofeva.tgbotplanny.client.ReportProducerClient;
import com.prokofeva.tgbotplanny.dto.ReportRequest;
import com.prokofeva.tgbotplanny.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GiveMePlanCommand implements Command {
    private final ReportProducerClient reportProducerClient;
    private final ReportService reportService;

    @Override
    public String execute(String msg) {
        var request = generateRequest();
        var id = getReportId(request);
        return reportService.getContent(id);
    }

    private String getReportId(ReportRequest request) {
        return reportProducerClient.generateReportJson(request);
    }

    private ReportRequest generateRequest() {
        return ReportRequest.builder()
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(6))
                .build();
    }
}
