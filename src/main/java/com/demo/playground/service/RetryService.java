package com.demo.playground.service;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RetryService {

    private final WebClient.Builder webClientBuilder;

    @Value("${retry.target.url:http://localhost:8080/test/retry/target}")
    private String targetUrl;

    /**
     * Calls the target API with Retry mechanism.
     * The @Retry annotation is configured in application.properties under "backendB".
     */
    @Retry(name = "backendB", fallbackMethod = "fallback")
    public String callTargetApi(boolean fail) {
        System.out.println("RetryService: Calling Target API at " + LocalDateTime.now());
        String url = targetUrl + "?fail=" + fail;
        return webClientBuilder.build()
                .post()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String fallback(boolean fail, Throwable t) {
        System.out.println("RetryService: Fallback executed after retries exhausted.");
        return "Fallback response: Retry limit reached. Error: " + t.getMessage();
    }
}
