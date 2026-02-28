package com.demo.playground.controller.jpa;

import com.demo.playground.service.jpa.JpaDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/jpa")
@RequiredArgsConstructor
public class JpaDemoController {

    private final JpaDemoService jpaDemoService;

    @GetMapping("/lifecycle")
    public ResponseEntity<String> testLifecycle() {
        return ResponseEntity.ok(jpaDemoService.demonstrateLifecycle());
    }

    @GetMapping("/cache")
    public ResponseEntity<String> testCache() {
        return ResponseEntity.ok(jpaDemoService.demonstrateFirstLevelCache());
    }

    @GetMapping("/dirty-checking")
    public ResponseEntity<String> testDirtyChecking() {
        return ResponseEntity.ok(jpaDemoService.demonstrateDirtyChecking());
    }

    @GetMapping("/mappings")
    public ResponseEntity<String> testMappings() {
        return ResponseEntity.ok(jpaDemoService.demonstrateMappings());
    }

    @GetMapping("/inheritance")
    public ResponseEntity<String> testInheritance() {
        return ResponseEntity.ok(jpaDemoService.demonstrateInheritance());
    }
}
