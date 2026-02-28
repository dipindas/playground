package com.demo.playground.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class TimeoutService {

    private final WebClient.Builder webClientBuilder;

    @Value("${timeout.target.url:http://localhost:8080/test/timeout/target}")
    private String targetUrl;

    @Value("${api.call.timeout.ms:500}")
    private long timeoutMs;

    /**
     * Calls the target API with a specified delay.
     * This method uses WebClient's built-in timeout mechanism.
     *
     * @param delay Simulated delay passed to the target API.
     * @return Response from target API or fallback.
     */
    public String callTargetApi(int delay) {
        String url = targetUrl + "?delay=" + delay;

        log.info("Calling {} with WebClient timeout configured to {} ms", url, timeoutMs);

        return webClientBuilder.build()
                .post()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                // WebClient native timeout mechanism. If no signal is received within the duration,
                // it emits a TimeoutException.
                .timeout(Duration.ofMillis(timeoutMs))
                // Handles errors that match java.util.concurrent.TimeoutException
                // (which is what reactor.core.publisher.Mono.timeout throws by default).
                .onErrorResume(TimeoutException.class, e -> {
                    log.error("API call timed out after {} ms", timeoutMs);
                    return Mono.just(fallback(delay, e));
                })
                // Fallback for any other type of exception, such as connection refused
                .onErrorResume(Exception.class, e -> {
                    log.error("API call failed: {}", e.getMessage());
                    return Mono.just("Fallback response: Error occurred: " + e.getMessage());
                })
                .block(); // Blocks to match the synchronous controller signature in this demo
    }

    /**
     * Fallback method invoked when a TimeoutException occurs.
     *
     * @param delay Simulated delay passed to the target API.
     * @param t     The exception.
     * @return A fallback message.
     */
    public String fallback(int delay, Throwable t) {
        return "Fallback response: The API call timed out. The target API took longer than the configured timeout of " + timeoutMs + "ms. Error: " + t.getMessage();
    }
}
