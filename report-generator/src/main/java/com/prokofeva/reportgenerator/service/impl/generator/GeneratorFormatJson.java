package com.prokofeva.reportgenerator.service.impl.generator;

import com.prokofeva.reportgenerator.dto.EventDto;
import com.prokofeva.reportgenerator.dto.ReportRequest;
import com.prokofeva.reportgenerator.report.ReportDay;
import com.prokofeva.reportgenerator.report.ReportPeriod;
import com.prokofeva.reportgenerator.service.GeneratorFormat;
import com.prokofeva.reportgenerator.util.Util;
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
@Slf4j
@RequiredArgsConstructor
public class GeneratorFormatJson implements GeneratorFormat {

    @Override
    public String generate(ReportRequest request, List<EventDto> data) {
        if (Objects.equals(request.dateStart(), request.dateEnd())) {
            var report = buildDaysData(data);
            return Util.toJson(report);
        }
        var weekTitle = buildTitle(request);
        var report = ReportPeriod.builder()
                .title(weekTitle)
                .totalEvents(data.size())
                .reportDays(buildData(data))
                .created(LocalDateTime.now())
                .build();
        return Util.toJson(report);
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

    private String buildTitle(ReportRequest request) {
        return String.format("Period: [ %s ... %s ]", request.dateStart(), request.dateEnd());
    }

    private String buildDayTitle(LocalDate day) {
        return String.format("[%s] - %s",
                day.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                day.format(DateTimeFormatter.ISO_DATE));
    }
}
