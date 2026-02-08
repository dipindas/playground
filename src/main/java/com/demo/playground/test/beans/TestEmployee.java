package com.demo.playground.test.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TestEmployee {

    private Employee employee;

    public TestEmployee(Employee employee) {
        this.employee = employee;
    }

    @Autowired
    private Employee getNewEmployee;

    public void print() {
        ApplicationContext context = new AnnotationConfigApplicationContext(EmployeeConfig.class);
        // Grabbing the bean manually from the container
        Employee emp = context.getBean(Employee.class);
        System.out.println(emp.getName());
    }
}
