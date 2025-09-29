package com.prokofeva.notificationservice.report;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ReportWeek implements Report {

    private String weekTitle;
    private ReportDay[] reportDays;
    private int totalEvents;
    private LocalDateTime created;

}
