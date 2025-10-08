package com.prokofeva.notificationservice.service.impl;

import com.prokofeva.notificationservice.dto.EventDto;
import com.prokofeva.notificationservice.dto.EventsForReportRequest;
import com.prokofeva.notificationservice.dto.NotificationRequest;
import com.prokofeva.notificationservice.enums.ReportTypeCode;
import com.prokofeva.notificationservice.exceptions.ProcessFailException;
import com.prokofeva.notificationservice.report.Report;
import com.prokofeva.notificationservice.report.ReportDay;
import com.prokofeva.notificationservice.report.ReportPeriod;
import com.prokofeva.notificationservice.service.ReportGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportGeneratorImpl implements ReportGenerator {

    @Override
    public EventsForReportRequest buildRequestByType(ReportTypeCode reportTypeCode, NotificationRequest request) {
        try {
            var startDate = request.date() == null ? LocalDate.now() : request.date();
            var endDate = switch (reportTypeCode) {
                case DAY_REPORT -> startDate;
                case WEEK_REPORT -> startDate.plusDays(6);
                case MONTH_REPORT -> startDate.plusMonths(1);
                case FREE_REPORT -> Objects.isNull(request.endDate()) ? LocalDate.now() : request.endDate();
            };
            return EventsForReportRequest.builder()
                    .dateStart(startDate)
                    .dateEnd(endDate)
                    .owners(Objects.isNull(request.owners()) ? List.of() : request.owners())
                    .eventType(request.eventType())
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ProcessFailException("Failed to build event request. Caused by: {}" + e.getMessage());
        }
    }

    @Override
    public Report generateReport(EventsForReportRequest requestToDb, List<EventDto> data) {
        if (Objects.equals(requestToDb.dateStart(), requestToDb.dateEnd())) {
            return buildDaysData(data);
        }
        var weekTitle = buildTitle(requestToDb);
        return ReportPeriod.builder()
                .weekTitle(weekTitle)
                .totalEvents(data.size())
                .reportDays(buildData(data))
                .created(LocalDateTime.now())
                .build();
    }

    private ReportDay[] buildData(List<EventDto> data) {
        return data.stream()
                .collect(Collectors.groupingBy(EventDto::dateEvent))
                .values().stream()
                .map(this::buildDaysData)
                .toArray(ReportDay[]::new);
    }

    private ReportDay buildDaysData(List<EventDto> data) {
        var title = buildDayTitle(data.getFirst().dateEvent());
        return ReportDay.builder()
                .dayTitle(title)
                .totalEvent(data.size())
                .listEvent(data)
                .build();
    }

    private String buildTitle(EventsForReportRequest request) {
        return String.format("Period: [ %s ... %s ]", request.dateStart(), request.dateEnd());
    }

    private String buildDayTitle(LocalDate day) {
        return String.format("[%s] - %s",
                day.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ROOT),
                day.format(DateTimeFormatter.ISO_DATE));
    }

}
