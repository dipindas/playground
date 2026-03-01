package com.demo.playground.entity.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jpa_employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Uni-directional OneToOne. Employee is the "owner" of the relationship
    // because it holds the foreign key (address_id).
    // Internally: JPA creates an `address_id` column in `jpa_employee`.
    // When Employee is saved, Address is cascaded (CascadeType.ALL) and saved first,
    // then its ID is stored in the Employee table.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    // Uni-directional ManyToOne. Employee is the "owner" (holds the foreign key).
    // The target entity (Department) has no List<Employee> pointing back here.
    // Internally: The `jpa_employee` table will have a `department_id` column.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    // Bi-directional ManyToMany. Employee is the "owner" of the relationship
    // because it defines the @JoinTable.
    // Internally: JPA creates a junction/join table named `jpa_employee_project`.
    // It has two foreign keys: `employee_id` and `project_id`.
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "jpa_employee_project",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects = new HashSet<>();

    public Employee(String name) {
        this.name = name;
    }

    public void addProject(Project project) {
        projects.add(project);
        project.getEmployees().add(this);
    }

    public void removeProject(Project project) {
        projects.remove(project);
        project.getEmployees().remove(this);
    }
}
