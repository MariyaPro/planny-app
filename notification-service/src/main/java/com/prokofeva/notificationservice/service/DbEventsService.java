package com.prokofeva.notificationservice.service;

import com.prokofeva.notificationservice.dto.EventDto;
import com.prokofeva.notificationservice.dto.EventsForReportRequest;

import java.util.List;

public interface DbEventsService {
    List<EventDto> getDataForReport(EventsForReportRequest request);
}
