package com.demo.playground.config;

import com.demo.playground.entity.Department;
import com.demo.playground.entity.Employee;
import com.demo.playground.entity.User;
import com.demo.playground.repository.DepartmentRepository;
import com.demo.playground.repository.EmployeeRepository;
import com.demo.playground.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("password"));
            userRepository.save(admin);
            System.out.println("Default user 'admin' created with password 'password'");
        }

        if (departmentRepository.count() == 0) {
            Department hr = new Department("HR");
            Department it = new Department("IT");
            Department sales = new Department("Sales");
            Department finance = new Department("Finance"); // Finance has no employees initially

            departmentRepository.saveAll(List.of(hr, it, sales, finance));

            Employee alice = new Employee("Alice", it);
            Employee bob = new Employee("Bob", it);
            Employee charlie = new Employee("Charlie", hr);
            Employee david = new Employee("David", sales);
            Employee eve = new Employee("Eve", null); // No department
            Employee grace = new Employee("Grace", null); // No department

            employeeRepository.saveAll(List.of(alice, bob, charlie, david, eve, grace));

            System.out.println("Sample data for Departments and Employees created.");
        }
    }
}
