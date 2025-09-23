package com.prokofeva.notificationservice.facade;

import com.prokofeva.notificationservice.dto.NotificationRequest;
import com.prokofeva.notificationservice.service.DbEventsService;
import com.prokofeva.notificationservice.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class NotificationFacade {
    private NotificationService notificationService;
    private DbEventsService dbEventsService;


    public void sendNotification(@Valid NotificationRequest request) {

    }
}
