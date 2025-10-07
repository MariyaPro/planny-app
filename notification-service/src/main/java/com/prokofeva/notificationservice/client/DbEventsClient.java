package com.prokofeva.notificationservice.client;

import com.prokofeva.notificationservice.config.DbEventsFeignConfig;
import com.prokofeva.notificationservice.dto.EventDto;
import com.prokofeva.notificationservice.dto.EventsForReportRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "dbPlannyService",
        url = "${services.db-planny-service}",
        configuration = DbEventsFeignConfig.class
)
public interface DbEventsClient {
    @PostMapping("/events/eventsForReport")
    List<EventDto> getDataForReport(@RequestBody EventsForReportRequest requestBody);
}
