package com.prokofeva.dbplannyservice.service;

import com.prokofeva.dbplannyservice.dto.EventDto;
import com.prokofeva.dbplannyservice.enums.Category;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    void createEvent(EventDto eventDto);

    List<EventDto> getEventsPeriod(LocalDate startPeriod, LocalDate endPeriod);

    List<EventDto> getEventsByCategory(Category category);
}