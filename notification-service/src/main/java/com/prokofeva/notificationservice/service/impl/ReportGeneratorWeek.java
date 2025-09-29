package com.prokofeva.notificationservice.service.impl;

import com.prokofeva.notificationservice.client.DbEventsClient;
import com.prokofeva.notificationservice.dto.EventDto;
import com.prokofeva.notificationservice.report.Report;
import com.prokofeva.notificationservice.report.ReportDay;
import com.prokofeva.notificationservice.report.ReportWeek;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ReportGeneratorWeek extends ReportGeneratorBase {

    public ReportGeneratorWeek(DbEventsClient dbEventsClient) {
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
    Report generateReport(@NotNull List<@Valid EventDto> data) {
        var weekTitle = buildWeekTitle(data.getFirst().dateStart());
        return ReportWeek.builder()
                .weekTitle(weekTitle)
                .reportDays(buildData(data))
                .totalEvents(data.size())
                .created(LocalDateTime.now())
                .build();
    }

    private ReportDay[] buildData(List<@Valid EventDto> data) {
        return data.stream()
                .collect(Collectors.groupingBy(EventDto::dateStart))
                .values().stream()
                .map(this::buildDaysData)
                .toArray(ReportDay[]::new);
    }

    private ReportDay buildDaysData(List<@Valid EventDto> data) {
        return ReportDay.builder()
                .dayTitle(buildDayTitle(data.getFirst().dateStart()))
                .totalEvent(data.size())
                .listEvent(data)
                .build();
    }

    private String buildWeekTitle(@NotNull LocalDate dateStart) {
        var monday = dateStart.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        var sunday = dateStart.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return String.format("Week: %s + %s.", monday, sunday);
    }

    private String buildDayTitle(@NotNull LocalDate day) {
        return String.format("[%s] - %s",
                day.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                day.format(DateTimeFormatter.ISO_DATE));
    }

}
