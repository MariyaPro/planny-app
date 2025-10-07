package com.prokofeva.notificationservice.service.impl.reportGenerator;

import com.prokofeva.notificationservice.client.DbEventsClient;
import com.prokofeva.notificationservice.dto.EventDto;
import com.prokofeva.notificationservice.report.Report;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportGeneratorDay extends ReportGeneratorBase {

    public ReportGeneratorDay(DbEventsClient dbEventsClient) {
        super(dbEventsClient);
    }

    @Override
    LocalDate getStartDate(LocalDate day) {
        return day;
    }

    @Override
    LocalDate getEndDate(LocalDate day) {
        return day;
    }

    @Override
    List<String> getOwners(List<String> ownersRequest) {
        return List.of();
    }

    @Override
    Report generateReport(List<@Valid EventDto> data) {
        return null;
    }
}
