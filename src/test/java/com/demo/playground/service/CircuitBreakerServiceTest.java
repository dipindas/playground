package com.demo.playground.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
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

@SpringBootTest(properties = {
    "resilience4j.circuitbreaker.instances.backendA.slidingWindowSize=10",
    "resilience4j.circuitbreaker.instances.backendA.minimumNumberOfCalls=5",
    "resilience4j.circuitbreaker.instances.backendA.permittedNumberOfCallsInHalfOpenState=3",
    "resilience4j.circuitbreaker.instances.backendA.failureRateThreshold=50"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CircuitBreakerServiceTest {

    @Autowired
    private CircuitBreakerService circuitBreakerService;

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
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.retrieve()).thenReturn(responseSpec);
    }

    @Test
    public void testCallTargetApiSuccess() {
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just("Success"));

        String response = circuitBreakerService.callTargetApi(false);

        assertEquals("Success", response);
        verify(webClient, times(1)).post();
    }

    @Test
    public void testCallTargetApiFailureFallback() {
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.error(new RuntimeException("Simulated Error")));

        String response = circuitBreakerService.callTargetApi(true);

        assertTrue(response.startsWith("Fallback response: Target API is down or unavailable"));
    }

    @Test
    public void testCircuitBreakerOpens() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendA");

        // Simulate failures to trip the circuit breaker (threshold is 50%, sliding window is 10, min calls is 5)
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.error(new RuntimeException("Simulated Error")));

        // Make calls to trigger failure threshold
        for (int i = 0; i < 15; i++) {
            circuitBreakerService.callTargetApi(true);
        }

        // Verify that the circuit breaker is now open
        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());

        // Verify that subsequent calls are blocked and fallback is executed without calling WebClient
        String response = circuitBreakerService.callTargetApi(false);
        assertTrue(response.startsWith("Fallback response: Target API is down or unavailable"));
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

        // Make calls to trigger failure threshold in HALF_OPEN (permitted calls is 3)
        for (int i = 0; i < 3; i++) {
            circuitBreakerService.callTargetApi(true);
        }

        // Verify it went back to OPEN
        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());
    }
}
