package com.demo.playground.service.impl;

import com.demo.playground.service.TestService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class TestService2 implements TestService {
    @Override
    public void print() {
        System.out.println("Inside test service 2");
    }
}
