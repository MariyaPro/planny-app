package com.prokofeva.dbplannyservice.service.impl;

import com.prokofeva.dbplannyservice.dto.EventDto;
import com.prokofeva.dbplannyservice.dto.EventsForReportRequest;
import com.prokofeva.dbplannyservice.entity.Event;
import com.prokofeva.dbplannyservice.mapper.MapperEvent;
import com.prokofeva.dbplannyservice.repository.EventRepository;
import com.prokofeva.dbplannyservice.service.EventService;
import com.prokofeva.dbplannyservice.specifications.EventSpecifications;
import com.prokofeva.dbplannyservice.util.LogRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.prokofeva.dbplannyservice.util.Util.toJson;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final MapperEvent mapperEvent;

    @Override
    public void createEvent(@NotEmpty List<EventDto> eventDtoList) {
        var entity = eventRepository.saveAll(eventDtoList.stream()
                .map(mapperEvent::toEntity)
                .toList());
        log.debug("Created new event: {}", toJson(entity));
    }

    @Override
    @LogRequest(level = LogRequest.LogLevel.DEBUG)
    public List<EventDto> getEventsPeriod(LocalDate startPeriod, LocalDate endPeriod) {
        return eventRepository.getEventsPeriod(startPeriod, endPeriod).stream()
                .map(mapperEvent::toDto)
                .toList();
    }

    @Override
    @LogRequest(level = LogRequest.LogLevel.DEBUG)
    public List<EventDto> getEventsByEventType(String eventType) {
        return eventRepository.findEventsByEventType_Name(eventType).stream()
                .map(mapperEvent::toDto)
                .toList();
    }

    @Override
    @LogRequest(level = LogRequest.LogLevel.DEBUG)
    public List<EventDto> getEventsForReport(EventsForReportRequest request) {
        Specification<Event> spec = Specification.allOf(
                EventSpecifications.dateAfterOrEqual(request.dateStart()),
                EventSpecifications.dateBeforeOrEqual(request.dateEnd()),
                EventSpecifications.isActive()
        );
        if (request.eventType() != null) {
            spec = spec.and(EventSpecifications.eventType(request.eventType()));
        }
        if (request.owners() != null && !request.owners().isEmpty()) {
            spec = spec.and(EventSpecifications.ownersInList(request.owners()));
        }
        return eventRepository.findAll(spec).stream()
                .map(mapperEvent::toDto)
                .toList();
    }

}
