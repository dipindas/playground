package com.demo.playground.controller;

import com.demo.playground.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    @Qualifier("test1")
    private TestService testService;

    @GetMapping("/api")
    public ResponseEntity<String> testController() {
        System.out.println("Inside test controller ");
        testService.print();
        return new ResponseEntity<>("Success response", HttpStatus.OK);
    }
}
