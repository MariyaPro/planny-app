package com.prokofeva.editorplannyui.service.impl;

import com.prokofeva.editorplannyui.client.DbEventsClient;
import com.prokofeva.editorplannyui.dto.EventDto;
import com.prokofeva.editorplannyui.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final DbEventsClient dbEventsClient;

    @Override
    public void save(EventDto eventDto) {
        eventDto.setActive(true);
        eventDto.setUpdated(LocalDateTime.now());
//        dbEventsClient.save(eventDto);
    }
}
