package com.prokofeva.tgbotplanny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TgBotPlannyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TgBotPlannyApplication.class, args);
    }

}
