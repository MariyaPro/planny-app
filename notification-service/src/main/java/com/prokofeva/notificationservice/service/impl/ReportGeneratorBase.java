package com.prokofeva.notificationservice.service.impl;


import com.prokofeva.notificationservice.client.DbEventsClient;
import com.prokofeva.notificationservice.dto.EventDto;
import com.prokofeva.notificationservice.dto.EventsForReportRequest;
import com.prokofeva.notificationservice.dto.NotificationRequest;
import com.prokofeva.notificationservice.report.Report;
import com.prokofeva.notificationservice.service.ReportGenerator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public abstract class ReportGeneratorBase implements ReportGenerator {
    private final DbEventsClient dbEventsClient;



    abstract LocalDate getStartDate(LocalDate day);

    abstract LocalDate getEndDate(LocalDate day);

    abstract List<String> getOwners(List<String> ownersRequest);

    abstract Report generateReport(List<@Valid EventDto> data);

    @Override
    public Mono<Report> process(NotificationRequest request) {
        var requestToDb = buildRequestByType(request);
        var dataFromDb = dbEventsClient.getData(requestToDb, getPath());
        return dataFromDb
                .collectList()
                .map(this::generateReport);
    }

    String getPath(){
        return "/events/eventsForReport";
    }

    private EventsForReportRequest buildRequestByType(NotificationRequest request) {
        return EventsForReportRequest.builder()
                .dateStart(getStartDate(request.date()))
                .dateEnd(getEndDate(request.date()))
                .owners(getOwners(request.owners()))
                .eventType(request.eventType())
                .build();
    }

}
