package com.prokofeva.notificationservice.service.impl;

import com.prokofeva.notificationservice.client.DbEventsClient;
import com.prokofeva.notificationservice.dto.EventDto;
import com.prokofeva.notificationservice.report.Report;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class ReportGeneratorPerson extends ReportGeneratorBase {

    public ReportGeneratorPerson(DbEventsClient dbEventsClient) {
        super(dbEventsClient);
    }

    @Override
    LocalDate getStartDate(LocalDate day) {
        return day.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    @Override
    LocalDate getEndDate(LocalDate day) {
        return day.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
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
