package com.prokofeva.dbplannyservice.service.impl;

import com.prokofeva.dbplannyservice.dto.EventDto;
import com.prokofeva.dbplannyservice.enums.Category;
import com.prokofeva.dbplannyservice.mapper.MapperEvent;
import com.prokofeva.dbplannyservice.repository.EventRepository;
import com.prokofeva.dbplannyservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.prokofeva.dbplannyservice.mapper.MapperEvent.toEntity;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public void createEvent(EventDto eventDto) {
        eventRepository.save(toEntity(eventDto));
    }

    @Override
    public List<EventDto> getEventsPeriod(LocalDate startPeriod, LocalDate endPeriod) {
        return eventRepository.getEventsPeriod(startPeriod,endPeriod).stream()
                .map(MapperEvent::toDto)
                .toList();
    }

    @Override
    public List<EventDto> getEventsByCategory(Category category) {
        return eventRepository.getAllByCategory(category).stream()
                .map(MapperEvent::toDto)
                .toList();
    }
}
