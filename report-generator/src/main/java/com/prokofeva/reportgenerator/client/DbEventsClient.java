package com.prokofeva.reportgenerator.client;

import com.prokofeva.reportgenerator.dto.EventDto;
import com.prokofeva.reportgenerator.dto.ReportRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "dbPlannyService",
        url = "${services.db-planny-service}"
)
public interface DbEventsClient {
    @PostMapping("/events/eventsForReport")
    List<EventDto> getDataForReport(@RequestBody ReportRequest requestBody);
}
