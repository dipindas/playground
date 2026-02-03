package com.demo.playground.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service to demonstrate Circuit Breaker implementation using Resilience4j.
 * This service makes an HTTP call to another API endpoint within the same application.
 */
@Service
@RequiredArgsConstructor
public class CircuitBreakerService {

    private final RestTemplate restTemplate;

    // The URL of the target API we are calling.
    // In a real microservice architecture, this would be the URL of another service.
    @Value("${circuit-breaker.target.url:http://localhost:8080/test/circuit-breaker/target}")
    private String targetUrl;

    /**
     * Calls the target API. This method is protected by a Circuit Breaker named "backendA".
     * <p>
     * How it works:
     * 1. The @CircuitBreaker annotation wraps this method call.
     * 2. If the call succeeds, it returns the value normally.
     * 3. If the call fails (throws an exception), the failure is recorded.
     * 4. If the failure rate exceeds the configured threshold, the Circuit Breaker transitions to OPEN state.
     * 5. In OPEN state, calls are immediately blocked (CircuitBreakerOpenException) without hitting the real API,
     *    and the fallback method is executed directly.
     * 6. After a wait duration, it transitions to HALF_OPEN to test if the service is back up.
     * </p>
     *
     * @param fail 'true' to force the target API to fail (simulating an error).
     * @return Response from target API or the fallback message.
     */
    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
    public String callTargetApi(boolean fail) {
        String url = targetUrl + "?fail=" + fail;
        // Making a synchronous HTTP call
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * Fallback method executed when the circuit breaker is OPEN or when the method throws an exception.
     * <p>
     * The signature must match the original method (parameters) + optionally the Throwable.
     * </p>
     *
     * @param fail The argument from the original method.
     * @param t The exception that caused the failure (e.g., ResourceAccessException, HttpServerErrorException, or CallNotPermittedException).
     * @return A fallback response indicating the service is unavailable.
     */
    public String fallback(boolean fail, Throwable t) {
        return "Fallback response: Target API is down or unavailable. Error: " + t.getMessage();
    }
}
