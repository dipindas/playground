package com.demo.playground.controller;

import com.demo.playground.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/joins")
@RequiredArgsConstructor
public class JoinDemoController {

    private final JoinService joinService;

    @GetMapping("/inner")
    public List<Map<String, Object>> getInnerJoin() {
        return joinService.getInnerJoin();
    }

    @GetMapping("/left")
    public List<Map<String, Object>> getLeftJoin() {
        return joinService.getLeftJoin();
    }

    @GetMapping("/right")
    public List<Map<String, Object>> getRightJoin() {
        return joinService.getRightJoin();
    }

    @GetMapping("/full")
    public List<Map<String, Object>> getFullJoin() {
        return joinService.getFullJoin();
    }
}
