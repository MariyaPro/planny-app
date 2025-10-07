package com.prokofeva.notificationservice.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbEventsFeignConfig {


    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(
                1000,
                2000,
                3
        );
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            template.header("Content-Type", "application/json");
            template.header("Accept", "application/json");
        };
    }

}
