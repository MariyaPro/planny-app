package com.prokofeva.editorplannyui.client;

import com.prokofeva.editorplannyui.dto.EventRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(
        name = "dbPlannyService",
        url = "${services.db-planny-service}"
)
public interface DbEventsClient {
    @PostMapping("/events/")
    void save(@RequestBody EventRequest eventRequest);
}
