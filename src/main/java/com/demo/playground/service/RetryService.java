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
     * Calls the target API. This method is protected by a Retry mechanism named "retryApi".
     *
     * @param fail 'true' to force the target API to fail (simulating an error).
     * @return Response from target API.
     */
    @Retry(name = "retryApi", fallbackMethod = "fallback")
    public String callTargetApi(boolean fail) {
        System.out.println("RetryService.callTargetApi called at " + LocalDateTime.now());
        String url = targetUrl + "?fail=" + fail;
        return webClientBuilder.build()
                .post()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String fallback(boolean fail, Throwable t) {
        return "Fallback response: All retry attempts failed. Error: " + t.getMessage();
    }
}
