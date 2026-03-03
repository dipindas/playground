package com.demo.playground.entity.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jpa_department")
@Getter
@Setter
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Bi-directional OneToMany. Desk is the owner (has @ManyToOne).
    // The "mappedBy" attribute means Department is the inverse (non-owning) side.
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Desk> desks = new ArrayList<>();

    // Bi-directional OneToMany. Employee is the owner (has @ManyToOne).
    // The "mappedBy" attribute means Department is the inverse (non-owning) side.
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public void addDesk(Desk desk) {
        desks.add(desk);
        desk.setDepartment(this);
    }

    public void removeDesk(Desk desk) {
        desks.remove(desk);
        desk.setDepartment(null);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setDepartment(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setDepartment(null);
    }
}
