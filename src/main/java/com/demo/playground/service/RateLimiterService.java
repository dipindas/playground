package com.demo.playground.service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.stereotype.Service;

@Service
public class RateLimiterService {

    @RateLimiter(name = "rateLimiterApi", fallbackMethod = "rateLimitFallback")
    public String processRequest() {
        return "Request processed successfully";
    }

    public String rateLimitFallback(RequestNotPermitted t) {
        return "Too many requests - Rate limit exceeded";
    }
}
