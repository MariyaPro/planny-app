package com.prokofeva.notificationservice.service;

import com.prokofeva.notificationservice.dto.EventDto;
import com.prokofeva.notificationservice.enums.ReportTypeCode;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public interface DbEventsService {

    List<EventDto> getEvents(@NotNull ReportTypeCode reportTypeCode, LocalDate dateRequest);
}
