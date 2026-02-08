package com.demo.playground.test.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {

    @Bean
    public Employee getNewEmployee() {
        return new Employee();
    }
}
