package com.prokofeva.notificationservice.scheduler;

import com.prokofeva.notificationservice.dto.NotificationRequest;
import com.prokofeva.notificationservice.facade.NotificationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventScheduler {
    private final NotificationFacade notificationFacade;

    @Scheduled(cron = "${cron.daily-report}")
    public void sendDailyReport() {
        log.info("Sending Daily report");
        notificationFacade.sendNotification(NotificationRequest.builder()
                .periodName("day")
                .build());
    }

    @Scheduled(cron = "${cron.weekly-report}")
    public void sendWeeklyReport() {
        log.info("Sending Weekly report");
        notificationFacade.sendNotification(NotificationRequest.builder()
                .periodName("week")
                .date(LocalDate.now().plusDays(1))
                .build());
    }
}
