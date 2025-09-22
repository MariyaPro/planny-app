package com.prokofeva.dbplannyservice.service.impl;

import com.prokofeva.dbplannyservice.entity.EventType;
import com.prokofeva.dbplannyservice.repository.EventTypeRepository;
import com.prokofeva.dbplannyservice.service.EventTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventTypeServiceImpl implements EventTypeService {
    private final EventTypeRepository eventTypeRepository;

    @Override
    public EventType findEventTypeByName(String name) {
        return eventTypeRepository.findEventTypeByName(name);
    }
}
