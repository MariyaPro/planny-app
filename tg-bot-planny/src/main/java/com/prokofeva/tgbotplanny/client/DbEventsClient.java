package com.prokofeva.tgbotplanny.client;

import com.prokofeva.tgbotplanny.dto.OwnerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(
        name = "dbPlannyService",
        url = "${services.db-planny-service}"
)
public interface DbEventsClient {

    @PostMapping("/owners/")
    UUID createOwner(@RequestBody OwnerDto ownerDto);
}
