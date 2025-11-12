package com.prokofeva.editorplannyui.client;

import com.prokofeva.editorplannyui.dto.EventRequest;
import com.prokofeva.editorplannyui.dto.OwnerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(
        name = "dbPlannyService",
        url = "${services.db-planny-service}"
)
public interface DbPlannyClient {
    @PostMapping("/events/")
    void save(@RequestBody EventRequest eventRequest);

    @GetMapping("/owners/")
    List<OwnerDto> getOwnersList(@RequestParam(value = "test",required = false) boolean isDemo);
}
