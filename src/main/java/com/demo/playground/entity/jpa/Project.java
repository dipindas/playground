package com.demo.playground.entity.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jpa_project")
@Getter
@Setter
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Bi-directional ManyToMany. Employee is the owner (has @JoinTable).
    // The "mappedBy" attribute means Project is the inverse (non-owning) side.
    // Changes made to the `employees` collection here will NOT be synchronized
    // to the database join table unless the owning side (Employee) is also updated.
    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();

    public Project(String name) {
        this.name = name;
    }
}
