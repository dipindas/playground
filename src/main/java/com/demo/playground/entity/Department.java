package com.demo.playground.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("department") // Avoid recursion by ignoring the department field in Employee when serializing Department
    private List<Employee> employees = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }
}
