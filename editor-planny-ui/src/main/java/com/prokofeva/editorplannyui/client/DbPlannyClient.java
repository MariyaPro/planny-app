package com.prokofeva.editorplannyui.client;

import com.prokofeva.editorplannyui.dto.EventRequest;
import com.prokofeva.editorplannyui.dto.OwnerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(
        name = "dbPlannyService",
        url = "${services.db-planny-service}"
)
public interface DbPlannyClient {
    @PostMapping("/events/")
    void save(@RequestBody EventRequest eventRequest);

    @GetMapping("/events/owners")
    List<OwnerDto> getOwnersList();
}
