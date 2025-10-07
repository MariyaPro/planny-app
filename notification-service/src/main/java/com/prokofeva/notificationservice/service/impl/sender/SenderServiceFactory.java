package com.prokofeva.notificationservice.service.impl.sender;

import com.prokofeva.notificationservice.service.SenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SenderServiceFactory {
    private final SenderEmailServiceImpl senderEmailService;
    private final SenderKafkaServiceImpl senderKafkaService;
    private final SenderTgServiceImpl senderTgService;

    public SenderService getSender(String deliveryMethod) {
        return switch (deliveryMethod) {
            case ("email") -> senderEmailService;
            case ("kafka") -> senderKafkaService;
            case ("tg") -> senderTgService;
            default -> throw new IllegalStateException("Unexpected value: " + deliveryMethod);
        };
    }
}
