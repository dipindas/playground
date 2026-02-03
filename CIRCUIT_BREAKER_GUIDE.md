# Circuit Breaker Implementation Guide

This guide explains the Circuit Breaker pattern implemented in this project using **Resilience4j**.

## Overview

The Circuit Breaker pattern prevents an application from repeatedly trying to execute an operation that's likely to fail. It allows your application to handle failures gracefully and prevents cascading failures in a distributed system.

### Components

1.  **Caller API** (`/test/circuit-breaker/caller`): The client-facing endpoint. It calls the Service.
2.  **Service** (`CircuitBreakerService`): Contains the business logic and wraps the external call with `@CircuitBreaker`.
3.  **Target API** (`/test/circuit-breaker/target`): Simulates a downstream service.

### Configuration (`application.properties`)

The Circuit Breaker "backendA" is configured with:
*   **Sliding Window**: 10 calls.
*   **Failure Threshold**: 50% (if 50% of calls fail, circuit opens).
*   **Wait Duration**: 5 seconds (circuit stays open for 5s before trying again).
*   **Minimum Calls**: 5 (needs at least 5 calls to calculate failure rate).

## How to Test

1.  **Start the Application**.
2.  **Normal Success Call**:
    *   GET `http://localhost:8080/test/circuit-breaker/caller`
    *   Response: "Target API responded successfully!"
3.  **Simulate Failure** (Repeat 5+ times):
    *   GET `http://localhost:8080/test/circuit-breaker/caller?fail=true`
    *   The internal call to Target API fails.
    *   The Service catches the error and eventually the Circuit Breaker counts the failure.
    *   Response: "Fallback response: Target API is down..."
4.  **Circuit Open**:
    *   After enough failures (>= 50% of last 5+ calls), the Circuit Breaker opens.
    *   GET `http://localhost:8080/test/circuit-breaker/caller` (without `fail=true`)
    *   Response: "Fallback response..." (Even though the target would succeed, the call is blocked).
5.  **Recovery**:
    *   Wait 5 seconds.
    *   GET `http://localhost:8080/test/circuit-breaker/caller`
    *   The Circuit Breaker allows a test request (Half-Open).
    *   If successful, it closes the circuit and returns to normal operation.
