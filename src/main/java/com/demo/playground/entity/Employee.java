package com.demo.playground.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER) // Eager for simplicity in demo
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties("employees") // Avoid recursion by ignoring the back reference list
    private Department department;

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }
}
