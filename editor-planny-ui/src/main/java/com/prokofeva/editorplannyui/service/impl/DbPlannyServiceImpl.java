package com.prokofeva.editorplannyui.service.impl;

import com.prokofeva.editorplannyui.client.DbPlannyClient;
import com.prokofeva.editorplannyui.dto.EventDto;
import com.prokofeva.editorplannyui.dto.EventForm;
import com.prokofeva.editorplannyui.dto.EventRequest;
import com.prokofeva.editorplannyui.enums.Recurrence;
import com.prokofeva.editorplannyui.service.DbPlannyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DbPlannyServiceImpl implements DbPlannyService {
    private final DbPlannyClient dbPlannyClient;

    @Override
    public void save(EventForm eventForm) {
        var eventRequest = EventRequest.builder().eventDtos(buildListDto(eventForm)).build();
        dbPlannyClient.save(eventRequest);
    }

    private List<EventDto> buildListDto(EventForm eventForm) {
        var eventDto = EventDto.builder()
                .title(eventForm.title())
                .ownerId(eventForm.ownerId())
                .eventTypeName(eventForm.eventTypeName())
                .dateEvent(eventForm.startEvent())
                .startTime(eventForm.startTime())
                .endTime(eventForm.endTime())
                .description(eventForm.description())
                .location(eventForm.location())
                .active(true)
                .build();
        var result = new ArrayList<EventDto>();
        result.add(eventDto);
        if (eventForm.recurrence() != null) {
            var endEvent = eventForm.endEvent();
            var recurrence = eventForm.recurrence();
            var curDate = nextDate(eventForm.startEvent(), recurrence);
            while (!curDate.isAfter(endEvent)) {
                var event = EventDto.builder()
                        .title(eventForm.title())
                        .ownerId(eventForm.ownerId())
                        .eventTypeName(eventForm.eventTypeName())
                        .dateEvent(curDate)
                        .startTime(eventForm.startTime())
                        .endTime(eventForm.endTime())
                        .description(eventForm.description())
                        .location(eventForm.location())
                        .active(true)
                        .build();
                result.add(event);
                curDate = nextDate(curDate, recurrence);
            }
        }
        return result;
    }

    private LocalDate nextDate(LocalDate localDate, Recurrence recurrence) {
        return switch (recurrence) {
            case DAILY -> localDate.plusDays(1);
            case WEEKLY -> localDate.plusWeeks(1);
            case MONTHLY -> localDate.plusMonths(1);
            case YEARLY -> localDate.plusYears(1);
        };
    }
}
