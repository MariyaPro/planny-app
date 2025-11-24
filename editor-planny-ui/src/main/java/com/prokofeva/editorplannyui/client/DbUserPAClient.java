package com.prokofeva.editorplannyui.client;

import com.prokofeva.editorplannyui.dto.OwnerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "dbUserPaService",
        url = "${services.db-user-pa-service}"
)
public interface DbUserPAClient {

    @GetMapping("/{id}/subscriptions")
    List<OwnerDto> getOwnersList(@PathVariable("id") long usedIdTg);
}