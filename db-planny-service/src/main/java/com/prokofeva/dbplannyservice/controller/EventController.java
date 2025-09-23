package com.prokofeva.dbplannyservice.controller;

import com.prokofeva.dbplannyservice.dto.EventDto;
import com.prokofeva.dbplannyservice.service.EventService;
import com.prokofeva.dbplannyservice.util.LogRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/db-planny/events")
@Slf4j
public class EventController {
    private final EventService eventService;

    @PostMapping("/")
    @LogRequest(logParameters = false)
    public ResponseEntity<Object> createEvent(@RequestBody @Valid EventDto eventDto) {
        eventService.createEvent(eventDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/period")
    @LogRequest
    public ResponseEntity<Object> getEventsPeriod(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startPeriod,
                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endPeriod) {
        return ResponseEntity.ok().body(eventService.getEventsPeriod(startPeriod, endPeriod));
    }

    @GetMapping("/category")
    @LogRequest(logParameters = false, logResult = false)
    public ResponseEntity<Object> getEventsByEventType(@RequestParam String eventType) {
        return ResponseEntity.ok().body(eventService.getEventsByEventType(eventType));
    }

}
