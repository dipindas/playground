package com.demo.playground.controller;

import com.demo.playground.service.CircuitBreakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to expose endpoints for testing the Circuit Breaker pattern.
 * Access these endpoints at /test/circuit-breaker/caller and /test/circuit-breaker/target.
 */
@RestController
@RequestMapping("/test/circuit-breaker")
@RequiredArgsConstructor
public class CircuitBreakerController {

    private final CircuitBreakerService circuitBreakerService;

    /**
     * Caller Endpoint: This is the client-facing API.
     * It delegates the logic to CircuitBreakerService, which calls the Target API.
     *
     * @param fail If true, the downstream Target API will fail, triggering the circuit breaker logic.
     * @return The response from the service (either success or fallback).
     */
    @GetMapping("/caller")
    public ResponseEntity<String> caller(@RequestParam(defaultValue = "false") boolean fail) {
        String response = circuitBreakerService.callTargetApi(fail);
        return ResponseEntity.ok(response);
    }

    /**
     * Target Endpoint: Represents the downstream service or API.
     *
     * @param fail If true, simulates a server error (500).
     * @return Success message or throws exception.
     */
    @GetMapping("/target")
    public ResponseEntity<String> target(@RequestParam(defaultValue = "false") boolean fail) {
        if (fail) {
            // Throwing RuntimeException will result in a 500 Internal Server Error by default in Spring Boot
            throw new RuntimeException("Simulated Downstream Service Failure");
        }
        return ResponseEntity.ok("Target API responded successfully!");
    }
}
