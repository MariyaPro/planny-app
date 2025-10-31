package com.prokofeva.reportgenerator.service.impl.generator;

import com.prokofeva.reportgenerator.dto.EventDto;
import com.prokofeva.reportgenerator.dto.ReportRequest;
import com.prokofeva.reportgenerator.service.GeneratorFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GeneratorFormatTxt implements GeneratorFormat {
    private static final Map<Integer, String> mapWeekRu = new HashMap<>();

    static {
        mapWeekRu.put(1, "Понедельник");
        mapWeekRu.put(2, "Вторник");
        mapWeekRu.put(3, "Среда");
        mapWeekRu.put(4, "Четверг");
        mapWeekRu.put(5, "Пятница");
        mapWeekRu.put(6, "Суббота");
        mapWeekRu.put(7, "Воскресенье");
    }

    @Override
    public String generate(ReportRequest request, List<EventDto> data) {
        var reportSb = new StringBuilder();
        if (Objects.equals(request.dateStart(), request.dateEnd())) {
            return buildDaysData(data).toString();
        }
        var weekTitle = buildTitle(request);
        reportSb.append(weekTitle)
                .append(String.format("Всего событий запланировано: %d.\n", data.size()));

        data.stream()
                .collect(Collectors.groupingBy(EventDto::dateEvent))
                .values()
                .stream().sorted(Comparator.comparing(l -> l.getFirst().dateEvent()))
                .forEach(listDay -> reportSb.append("-----------------\n").append(buildDaysData(listDay)));

        return reportSb.toString();
    }

    private StringBuilder buildDaysData(List<EventDto> dataDay) {
        var reportSb = new StringBuilder();
        var title = buildDayTitle(dataDay.getFirst().dateEvent());
        reportSb.append(title)
                .append(String.format("запланировано %d.\n", dataDay.size()));
        dataDay.forEach(event -> reportSb.append(buildEventDescription(event)));
        return reportSb;
    }

    private String buildEventDescription(EventDto eventDto) {
        var evDescription = new StringBuilder();
        evDescription.append(String.format("* %s\n", eventDto.title()))
                .append(String.format("\t\t\t\t\t\tКто: %s\n", eventDto.ownerName()));
//                .append(String.format("\t\t\t\t\t\tКатегория: %s\n", eventDto.eventTypeName()));
        if (eventDto.startTime() != null)
            evDescription.append(String.format("\t\t\t\t\t\tВремя: с %s", eventDto.startTime()));
        if (eventDto.endTime() != null)
            evDescription.append(String.format(" до %s\n", eventDto.endTime()));
        else evDescription.append("\n");
        if (eventDto.location() != null && !evDescription.isEmpty())
            evDescription.append(String.format("\t\t\t\t\t\tМесто: %s\n", eventDto.location()));
        if (eventDto.description() != null && !eventDto.description().isEmpty())
            evDescription.append(String.format("\t\t\t\t\t\tКомментарий: %s\n", eventDto.description()));
        return evDescription.toString();
    }

    private String buildDayTitle(LocalDate day) {
        return String.format("%s - %s :: ",
                day.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                mapWeekRu.get(day.getDayOfWeek().getValue()));
    }

    private String buildTitle(ReportRequest request) {
        return String.format("Период с %s по %s.\n",
                request.dateStart().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                request.dateEnd().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }
}
