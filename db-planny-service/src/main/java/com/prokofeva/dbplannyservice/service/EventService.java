package com.prokofeva.dbplannyservice.service;

import com.prokofeva.dbplannyservice.dto.EventDto;
import com.prokofeva.dbplannyservice.dto.ReportRequest;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    void createEvent(EventDto eventDto);

    List<EventDto> getEventsPeriod(LocalDate startPeriod, LocalDate endPeriod);

    List<EventDto> getEventsByEventType(String eventType);

    List<EventDto> getEventsForReport(@Valid ReportRequest request);
}