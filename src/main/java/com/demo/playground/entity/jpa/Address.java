package com.demo.playground.entity.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jpa_address")
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;

    // Bi-directional OneToOne. Employee is the owner (has @JoinColumn).
    @OneToOne(mappedBy = "address")
    private Employee employee;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }
}
