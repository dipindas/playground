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

    // Uni-directional OneToMany. Department is the owner because there is no @ManyToOne
    // on the Desk side back to Department.
    // Internally: JPA creates a separate join table (e.g., `jpa_department_jpa_desk`)
    // or uses a foreign key in the `jpa_desk` table if @JoinColumn is explicitly specified.
    // Here we specify @JoinColumn to force a foreign key in the `jpa_desk` table
    // rather than creating a whole join table just for a OneToMany relationship.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "department_id")
    private List<Desk> desks = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public void addDesk(Desk desk) {
        desks.add(desk);
    }

    public void removeDesk(Desk desk) {
        desks.remove(desk);
    }
}
