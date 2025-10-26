package com.prokofeva.tgbotplanny.facade;

import com.prokofeva.tgbotplanny.client.ReportProducerClient;
import com.prokofeva.tgbotplanny.dto.ReportRequest;
import com.prokofeva.tgbotplanny.enums.ReportTypeCode;
import com.prokofeva.tgbotplanny.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportFacade {
    private final ReportProducerClient reportProducerClient;
    private final ReportService reportService;

    public String getReport(CallbackQuery callbackQuery) {
        var userName = callbackQuery.getFrom().getFirstName();
        var reportTypeCode = ReportTypeCode.valueOf(ReportTypeCode.class, callbackQuery.getData());
        var request = generateRequest(reportTypeCode);
        var id = getReportId(request);
        return String.format("Вот твой план %s, %s:\n\n%s",reportTypeCode.getDescription(),userName,reportService.getContent(id));
    }

    private String getReportId(ReportRequest request) {
        return reportProducerClient.generateReportTxt(request);
    }

    private ReportRequest generateRequest(ReportTypeCode reportTypeCode) {
        var startDate = switch (reportTypeCode) {
//            case FREE_REPORT -> request.date();
            case WEEK_REPORT, MONTH_REPORT, TODAY_REPORT -> LocalDate.now();
            case TOMORROW_REPORT -> LocalDate.now().plusDays(1);
        };
        var endDate = switch (reportTypeCode) {
            case TODAY_REPORT, TOMORROW_REPORT -> startDate;
            case WEEK_REPORT -> startDate.plusDays(6);
            case MONTH_REPORT -> startDate.plusMonths(1);
//            case FREE_REPORT -> Objects.isNull(request.endDate()) ? LocalDate.now() : request.endDate();
        };
        return ReportRequest.builder()
                .dateStart(LocalDate.now())
                .dateEnd(endDate)
                .build();
    }

}
