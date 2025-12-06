package com.prokofeva.reportgenerator.client;

import com.prokofeva.reportgenerator.dto.OwnerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "dbUserPaService",
        url = "${services.db-user-pa-service}"
)
public interface DbUserPaClient {
    @GetMapping("/{idtg}/subscriptions")
    List<OwnerDto> getUserPASubscriptions(@PathVariable("idtg") long userIdTg);

}
