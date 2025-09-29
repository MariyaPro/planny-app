package com.prokofeva.dbplannyservice.service;

import com.prokofeva.dbplannyservice.dto.EventDto;
import com.prokofeva.dbplannyservice.dto.EventsForReportRequest;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    void createEvent(EventDto eventDto);

    List<EventDto> getEventsPeriod(LocalDate startPeriod, LocalDate endPeriod);

    List<EventDto> getEventsByEventType(String eventType);

    List<EventDto> getEventsForReport(@Valid EventsForReportRequest request);
}