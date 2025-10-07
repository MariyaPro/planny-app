package com.prokofeva.notificationservice.report;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ReportWeek implements Report {

    private String weekTitle;
    private int totalEvents;
    private ReportDay[] reportDays;
    private LocalDateTime created;

}
