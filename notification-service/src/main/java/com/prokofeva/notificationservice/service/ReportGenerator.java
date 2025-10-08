package com.prokofeva.notificationservice.service;

import com.prokofeva.notificationservice.dto.EventDto;
import com.prokofeva.notificationservice.dto.EventsForReportRequest;
import com.prokofeva.notificationservice.dto.NotificationRequest;
import com.prokofeva.notificationservice.enums.ReportTypeCode;
import com.prokofeva.notificationservice.report.Report;

import java.util.List;

public interface ReportGenerator {

    EventsForReportRequest buildRequestByType(ReportTypeCode reportTypeCode, NotificationRequest request);

    Report generateReport(EventsForReportRequest requestToDb, List<EventDto> dataFromDb);
}