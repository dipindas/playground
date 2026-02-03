package com.demo.playground.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CircuitBreakerServiceTest {

    @Autowired
    private CircuitBreakerService circuitBreakerService;

    @MockitoBean
    private WebClient.Builder webClientBuilder;

    @MockitoBean
    private WebClient webClient;

    // For POST, WebClient returns RequestBodyUriSpec
    @MockitoBean
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @MockitoBean
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @MockitoBean
    private WebClient.ResponseSpec responseSpec;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @BeforeEach
    public void setUp() {
        // Reset the circuit breaker state before each test
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendA");
        circuitBreaker.reset();

        // Setup WebClient mock chain for POST
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.retrieve()).thenReturn(responseSpec);
    }

    @Test
    public void testCallTargetApiSuccess() {
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just("Success"));

        String response = circuitBreakerService.callTargetApi(false);

        assertEquals("Success", response);
        verify(webClientBuilder, times(1)).build();
    }

    @Test
    public void testCallTargetApiFailureFallback() {
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.error(new RuntimeException("Simulated Error")));

        String response = circuitBreakerService.callTargetApi(true);

        assertTrue(response.startsWith("Fallback response: Target API is down or unavailable. Error: Simulated Error"));
    }

    @Test
    public void testCircuitBreakerOpens() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendA");

        // Simulate failures to trip the circuit breaker (threshold is 50%, min calls is 5)
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.error(new RuntimeException("Simulated Error")));

        // Make 5 calls to satisfy minimum number of calls
        for (int i = 0; i < 5; i++) {
            circuitBreakerService.callTargetApi(true);
        }

        // Verify that the circuit breaker is now open
        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());

        // Verify that subsequent calls are blocked and fallback is executed without calling WebClient
        String response = circuitBreakerService.callTargetApi(false);
        assertTrue(response.startsWith("Fallback response: Target API is down or unavailable. Error: CircuitBreaker 'backendA' is OPEN"));

        // WebClient should have been called 5 times (failures) but not for the 6th call
        verify(webClientBuilder, times(5)).build();
    }

    @Test
    public void testCircuitBreakerHalfOpenToOpen() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendA");

        // 1. Force Circuit Breaker to OPEN state
        circuitBreaker.transitionToOpenState();
        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());

        // 2. Transition to HALF_OPEN
        circuitBreaker.transitionToHalfOpenState();
        assertEquals(CircuitBreaker.State.HALF_OPEN, circuitBreaker.getState());

        // 3. Simulate a failure in HALF_OPEN state
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.error(new RuntimeException("Simulated Error in Half Open")));

        // Call 1: Fails (1/3 calls = 33% failure rate. Threshold is 50%. State remains HALF_OPEN)
        circuitBreakerService.callTargetApi(true);
        assertEquals(CircuitBreaker.State.HALF_OPEN, circuitBreaker.getState());

        // Call 2: Fails (2/3 calls = 66% failure rate. Threshold exceeded. State transitions to OPEN)
        circuitBreakerService.callTargetApi(true);

        // Call 3: Success (Just to complete the permitted calls if needed)
        // Note: Even if we mock success here, the failure rate is already 66% (2/3) which is > 50%
        // We need to consume the 3rd permitted call for the decision to be made
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just("Success"));
        circuitBreakerService.callTargetApi(false);

        // Verify it went back to OPEN
        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());

        // Verify subsequent call is rejected
        String response = circuitBreakerService.callTargetApi(false);
        assertTrue(response.startsWith("Fallback response"));
    }
}
