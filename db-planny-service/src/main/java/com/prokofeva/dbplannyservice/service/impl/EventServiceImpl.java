package com.prokofeva.dbplannyservice.service.impl;

import com.prokofeva.dbplannyservice.dto.EventDto;
import com.prokofeva.dbplannyservice.enums.Category;
import com.prokofeva.dbplannyservice.mapper.MapperEvent;
import com.prokofeva.dbplannyservice.repository.EventRepository;
import com.prokofeva.dbplannyservice.service.EventService;
import com.prokofeva.dbplannyservice.util.LogRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.prokofeva.dbplannyservice.mapper.MapperEvent.toEntity;
import static com.prokofeva.dbplannyservice.util.Util.toJson;


@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public void createEvent(EventDto eventDto) {
        var entity = eventRepository.save(toEntity(eventDto));
        log.debug("Created new event: {}", toJson(entity));
    }

    @Override
    @LogRequest(level = LogRequest.LogLevel.DEBUG)
    public List<EventDto> getEventsPeriod(LocalDate startPeriod, LocalDate endPeriod) {
        return eventRepository.getEventsPeriod(startPeriod, endPeriod).stream()
                .map(MapperEvent::toDto)
                .toList();
    }

    @Override
    @LogRequest(level = LogRequest.LogLevel.DEBUG)
    public List<EventDto> getEventsByCategory(Category category) {
        return eventRepository.getAllByCategory(category).stream()
                .map(MapperEvent::toDto)
                .toList();
    }
}
