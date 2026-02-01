package com.demo.playground.service.impl;

import com.demo.playground.service.TestService;
import org.springframework.stereotype.Service;

@Service("test1")
public class TestService1 implements TestService {
    @Override
    public void print() {
        System.out.println("Inside test service 1");
    }
}
