package com.prokofeva.notificationservice.client;

import com.prokofeva.notificationservice.dto.EventDto;
import com.prokofeva.notificationservice.dto.EventsForReportRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Slf4j
public class DbEventsClient {
    @Value("${services.db-planny-service}")
    private String baseUrl;
    private final WebClient webClient;

    public Flux<EventDto> getData(EventsForReportRequest requestBody, String path) {
        log.info("Request body: {}", requestBody);
        return webClient.post()
                .uri(baseUrl + path)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    log.error(response.toString());
                    return null;
                })
                .bodyToFlux(EventDto.class);
    }
}
