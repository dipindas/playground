package com.demo.playground.entity.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jpa_desk")
@Getter
@Setter
@NoArgsConstructor
public class Desk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String locationCode;

    // Bi-directional ManyToOne. Desk is the owner.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public Desk(String locationCode) {
        this.locationCode = locationCode;
    }
}
