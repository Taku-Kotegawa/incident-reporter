package com.example.incidentfrontend.incident.common;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientSample {
    private final WebClient webClient;

    public WebClientSample(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }
}