package com.demo.playground.controller;

import com.demo.playground.service.RetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/test/retry")
@RequiredArgsConstructor
public class RetryController {

    private final RetryService retryService;

    @GetMapping("/caller")
    public ResponseEntity<String> caller(@RequestParam(defaultValue = "false") boolean fail) {
        return ResponseEntity.ok(retryService.callTargetApi(fail));
    }

    @PostMapping("/target")
    public ResponseEntity<String> target(@RequestParam(defaultValue = "false") boolean fail) {
        if (fail) {
            System.out.println("Target API failed at " + LocalDateTime.now());
            throw new RuntimeException("Simulated Downstream Service Failure for Retry");
        }
        return ResponseEntity.ok("Target API responded successfully!");
    }
}
