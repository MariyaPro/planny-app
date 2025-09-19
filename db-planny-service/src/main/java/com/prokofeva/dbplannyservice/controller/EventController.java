package com.prokofeva.dbplannyservice.controller;

import com.prokofeva.dbplannyservice.dto.EventDto;
import com.prokofeva.dbplannyservice.enums.Category;
import com.prokofeva.dbplannyservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/planny/events")
public class EventController {
    private final EventService eventService;

    @PostMapping("/")
    public ResponseEntity<Object> createEvent(@RequestBody EventDto eventDto) {
        eventService.createEvent(eventDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/period")
    public ResponseEntity<Object> getEventsPeriod(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startPeriod,
                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endPeriod) {
        return ResponseEntity.ok().body(eventService.getEventsPeriod(startPeriod, endPeriod));
    }

    @GetMapping("/category")
    public ResponseEntity<Object> getEventsByCategory(@RequestParam Category category) {
        return ResponseEntity.ok().body(eventService.getEventsByCategory(category));
    }
}
