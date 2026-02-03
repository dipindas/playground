package com.demo.playground.controller;

import com.demo.playground.service.RetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/retry")
@RequiredArgsConstructor
public class RetryController {

    private final RetryService retryService;

    @GetMapping("/caller")
    public ResponseEntity<String> caller(@RequestParam(defaultValue = "false") boolean fail) {
        String response = retryService.callTargetApi(fail);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/target")
    public ResponseEntity<String> target(@RequestParam(defaultValue = "false") boolean fail) {
        if (fail) {
            throw new RuntimeException("Simulated Retry Failure");
        }
        return ResponseEntity.ok("Retry Target API responded successfully!");
    }
}
