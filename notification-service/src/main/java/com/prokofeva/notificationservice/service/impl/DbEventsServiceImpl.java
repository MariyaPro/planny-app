package com.prokofeva.notificationservice.service.impl;

import com.prokofeva.notificationservice.client.DbEventsClient;
import com.prokofeva.notificationservice.dto.EventDto;
import com.prokofeva.notificationservice.dto.EventsForReportRequest;
import com.prokofeva.notificationservice.exceptions.ProcessFailException;
import com.prokofeva.notificationservice.service.DbEventsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DbEventsServiceImpl implements DbEventsService {
    private final DbEventsClient dbEventsClient;


    @Override
    public List<EventDto> getDataForReport(EventsForReportRequest request) {
        try{
            return dbEventsClient.getDataForReport(request);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ProcessFailException("Failed to get data for report. Cause: " + e.getMessage());
        }
    }
}
