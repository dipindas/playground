package com.demo.playground.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    private RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @BeforeEach
    public void setUp() {
        // Reset the circuit breaker state before each test
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendA");
        circuitBreaker.reset();
    }

    @Test
    public void testCallTargetApiSuccess() {
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("Success");

        String response = circuitBreakerService.callTargetApi(false);

        assertEquals("Success", response);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(String.class));
    }

    @Test
    public void testCallTargetApiFailureFallback() {
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenThrow(new RuntimeException("Simulated Error"));

        String response = circuitBreakerService.callTargetApi(true);

        assertTrue(response.startsWith("Fallback response: Target API is down or unavailable. Error: Simulated Error"));
        verify(restTemplate, times(1)).getForObject(anyString(), eq(String.class));
    }

    @Test
    public void testCircuitBreakerOpens() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendA");

        // Simulate failures to trip the circuit breaker (threshold is 50%, min calls is 5)
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenThrow(new RuntimeException("Simulated Error"));

        for (int i = 0; i < 10; i++) {
            circuitBreakerService.callTargetApi(true);
        }

        // Verify that the circuit breaker is now open
        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());

        // Verify that subsequent calls are blocked and fallback is executed without calling RestTemplate
        String response = circuitBreakerService.callTargetApi(false);
        assertTrue(response.startsWith("Fallback response: Target API is down or unavailable. Error: CircuitBreaker 'backendA' is OPEN"));

        // RestTemplate should have been called 5 times (minimumNumberOfCalls) because circuit opens after that
        verify(restTemplate, times(5)).getForObject(anyString(), eq(String.class));
    }
}
