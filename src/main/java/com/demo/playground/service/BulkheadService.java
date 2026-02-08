package com.demo.playground.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class BulkheadService {

    private final WebClient.Builder webClientBuilder;

    @Value("${bulkhead.target.url:http://localhost:8080/test/bulkhead/target}")
    private String targetUrl;

    /**
     * Calls the target API. This method is protected by a Bulkhead mechanism named "bulkheadApi".
     *
     * @return Response from target API.
     */
    @Bulkhead(name = "bulkheadApi", fallbackMethod = "fallback")
    public String callTargetApi() {
        return webClientBuilder.build()
                .post()
                .uri(targetUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String fallback(Throwable t) {
        return "Fallback response: Bulkhead is full. Error: " + t.getMessage();
    }
}
