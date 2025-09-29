package com.prokofeva.notificationservice.report;

import com.prokofeva.notificationservice.dto.EventDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ReportDay implements Report {

    private String dayTitle;
    private int totalEvent;
    private List<EventDto> listEvent;

}
