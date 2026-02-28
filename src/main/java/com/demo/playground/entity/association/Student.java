package com.demo.playground.entity.association;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Demonstrates the non-owning side of a @ManyToMany relationship.
 */
@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // The mappedBy indicates that Student does NOT own the relationship.
    // The Course entity owns the join table.
    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();
}
