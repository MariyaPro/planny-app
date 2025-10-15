package com.prokofeva.tgbotplanny.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "reportProducerService",
        url = "${services.db-planny-service}"
)
public interface ReportProducerClient {
    //todo
//    @PostMapping("/events/eventsForReport")
//    List<EventDto> getDataForReport(@RequestBody EventsForReportRequest requestBody);
}
