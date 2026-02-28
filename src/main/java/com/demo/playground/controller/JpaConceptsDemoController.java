package com.demo.playground.controller;

import com.demo.playground.service.JpaConceptsDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to trigger the JPA Concepts demonstration.
 * Shows responses detailing the steps of each demonstration.
 */
@RestController
@RequestMapping("/test/jpa")
@RequiredArgsConstructor
public class JpaConceptsDemoController {

    private final JpaConceptsDemoService jpaConceptsDemoService;

    @GetMapping("/lifecycle")
    public ResponseEntity<String> testLifecycle() {
        return ResponseEntity.ok(jpaConceptsDemoService.demonstrateLifecycleAndPersistenceContext());
    }

    @GetMapping("/dirty-checking")
    public ResponseEntity<String> testDirtyChecking() {
        return ResponseEntity.ok(jpaConceptsDemoService.demonstrateDirtyChecking());
    }

    @GetMapping("/associations")
    public ResponseEntity<String> testAssociations() {
        return ResponseEntity.ok(jpaConceptsDemoService.demonstrateAssociationsAndCascading());
    }
}
