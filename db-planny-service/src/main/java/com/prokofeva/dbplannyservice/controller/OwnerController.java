package com.prokofeva.dbplannyservice.controller;

import com.prokofeva.dbplannyservice.service.OwnerService;
import com.prokofeva.dbplannyservice.util.LogRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/db-planny/owners")
@Slf4j
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping("/")
    @LogRequest
    public ResponseEntity<Object> getOwners(@RequestParam(value = "test", required = false) boolean test) {
        return ResponseEntity.ok(ownerService.findAllActive(!test));
    }
}
