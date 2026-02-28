package com.demo.playground.controller;

import com.demo.playground.service.TimeoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/timeout")
@RequiredArgsConstructor
public class TimeoutController {

    private final TimeoutService timeoutService;

    /**
     * Endpoint to call the target API with a specified delay.
     * If the delay exceeds the configured timeout in the WebClient, a timeout exception occurs.
     *
     * @param delay Simulated delay in milliseconds.
     * @return Response indicating success or fallback.
     */
    @GetMapping("/caller")
    public ResponseEntity<String> caller(@RequestParam(defaultValue = "1000") int delay) {
        return ResponseEntity.ok(timeoutService.callTargetApi(delay));
    }

    /**
     * Target API that simulates varying response times.
     *
     * @param delay Simulated delay in milliseconds.
     * @return Response string.
     * @throws InterruptedException if thread is interrupted while sleeping.
     */
    @PostMapping("/target")
    public ResponseEntity<String> target(@RequestParam(defaultValue = "1000") int delay) throws InterruptedException {
        // Simulate processing time
        Thread.sleep(delay);
        return ResponseEntity.ok("Target API responded successfully after " + delay + " ms!");
    }
}
