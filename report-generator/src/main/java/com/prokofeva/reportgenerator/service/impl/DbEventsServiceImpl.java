package com.prokofeva.reportgenerator.service.impl;

import com.prokofeva.reportgenerator.client.DbEventsClient;
import com.prokofeva.reportgenerator.dto.EventDto;
import com.prokofeva.reportgenerator.dto.ReportRequest;
import com.prokofeva.reportgenerator.exceptions.ProcessFailException;
import com.prokofeva.reportgenerator.service.DbEventsService;
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
    public List<EventDto> getDataForReport(ReportRequest request) {
        try{
            return dbEventsClient.getDataForReport(request);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ProcessFailException("Failed to get data for report. Cause: " + e.getMessage());
        }
    }
}
