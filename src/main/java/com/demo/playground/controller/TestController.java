package com.demo.playground.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/api")
    public ResponseEntity<String> testController() {
        return new ResponseEntity<>("Success response", HttpStatus.OK);
    }
}
