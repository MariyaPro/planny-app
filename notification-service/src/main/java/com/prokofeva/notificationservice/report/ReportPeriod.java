package com.prokofeva.notificationservice.report;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ReportPeriod implements Report {

    private String title;
    private int totalEvents;
    private ReportDay[] reportDays;
    private LocalDateTime created;

}
