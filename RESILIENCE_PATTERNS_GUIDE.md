# Resilience4j Patterns Implementation Guide

This guide explains the Resilience4j patterns implemented in this project: **Circuit Breaker**, **Retry**, and **Bulkhead**.

## 1. Circuit Breaker

The Circuit Breaker pattern prevents an application from repeatedly trying to execute an operation that's likely to fail.

### Components
*   **Caller API**: `/test/circuit-breaker/caller`
*   **Target API**: `/test/circuit-breaker/target`
*   **Config**: `backendA` (50% failure rate, 5s wait).

### How to Test
1.  **Normal**: GET `/test/circuit-breaker/caller` -> "Target API responded successfully!"
2.  **Fail**: GET `/test/circuit-breaker/caller?fail=true` (Repeat 5+ times) -> "Fallback response..."
3.  **Open**: GET `/test/circuit-breaker/caller` (Success params) -> "Fallback response: CircuitBreaker 'backendA' is OPEN..."

---

## 2. Retry with Backoff/Jitter

The Retry pattern automatically retries a failed operation a configured number of times, often with a wait duration between attempts.

### Components
*   **Caller API**: `/test/retry/caller`
*   **Target API**: `/test/retry/target`
*   **Config**: `backendB` (Max 3 attempts, 1s wait, exponential backoff).

### How to Test
1.  **Normal**: GET `/test/retry/caller` -> "Retry Target API responded successfully!"
2.  **Fail**: GET `/test/retry/caller?fail=true`
    *   Check Console Logs. You should see "Calling Target API..." printed multiple times (approx 3 times) with increasing delays (1s, 2s...).
    *   Eventually: "Fallback response: Retry limit reached..."

---

## 3. Bulkhead

The Bulkhead pattern isolates parts of an application so that if one fails or becomes slow, it doesn't take down the whole system. It limits the number of concurrent calls to a service.

### Components
*   **Caller API**: `/test/bulkhead/caller`
*   **Target API**: `/test/bulkhead/target` (Simulates 500ms delay).
*   **Config**: `backendC` (Max 2 concurrent calls).

### How to Test
1.  **Single Call**: GET `/test/bulkhead/caller` -> "Bulkhead Target API responded successfully!"
2.  **Concurrency Test**:
    *   Use a tool like Apache Bench (`ab`) or JMeter to send > 2 concurrent requests.
    *   Example: `ab -n 10 -c 5 http://localhost:8080/test/bulkhead/caller`
    *   Result: Some requests will succeed (those that got a permit), while others will fail immediately with "Fallback response: Bulkhead full..." because the max concurrent limit (2) was exceeded.
