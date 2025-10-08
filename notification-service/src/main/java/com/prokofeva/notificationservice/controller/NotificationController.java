package com.prokofeva.notificationservice.controller;

import com.prokofeva.notificationservice.dto.NotificationRequest;
import com.prokofeva.notificationservice.facade.NotificationFacade;
import com.prokofeva.notificationservice.util.LogRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class NotificationController {
    private final NotificationFacade notificationFacade;

    @PostMapping("/reportByFilter")
    @LogRequest
    public ResponseEntity<Object> sendNotification(@RequestBody @Valid NotificationRequest request) {
        notificationFacade.sendNotification(request);
        return ResponseEntity.ok().build();
    }
}
