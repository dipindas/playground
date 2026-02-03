package com.demo.playground.controller;

import com.demo.playground.service.BulkheadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/bulkhead")
@RequiredArgsConstructor
public class BulkheadController {

    private final BulkheadService bulkheadService;

    @GetMapping("/caller")
    public ResponseEntity<String> caller() {
        return ResponseEntity.ok(bulkheadService.callTargetApi());
    }

    @PostMapping("/target")
    public ResponseEntity<String> target() throws InterruptedException {
        // Simulate processing time
        Thread.sleep(500);
        return ResponseEntity.ok("Target API responded successfully!");
    }
}
