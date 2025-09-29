package com.prokofeva.notificationservice.service.impl;

import com.prokofeva.notificationservice.client.DbEventsClient;
import com.prokofeva.notificationservice.dto.EventDto;
import com.prokofeva.notificationservice.enums.ReportTypeCode;
import com.prokofeva.notificationservice.service.DbEventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbEventsServiceImpl implements DbEventsService {
    private final DbEventsClient dbEventsClient;

    @Override
    public List<EventDto> getEvents(ReportTypeCode reportTypeCode, LocalDate dateRequest) {
        MultiValueMap<String,String> param = prepareParamByReportType(reportTypeCode,dateRequest);

        return List.of();
    }

    private MultiValueMap<String, String> prepareParamByReportType(ReportTypeCode reportTypeCode, LocalDate dateRequest) {
    MultiValueMap<String, String> param = new LinkedMultiValueMap<>();

    param.add("reportTypeCode", reportTypeCode.name());
    param.add("dateRequest", dateRequest.toString());
    return param;
    }
}
