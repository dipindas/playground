package com.demo.playground.entity.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jpa_dog")
@Getter
@Setter
@NoArgsConstructor
public class Dog extends Animal {

    private String breed;

    public Dog(String species, String breed) {
        super(species);
        this.breed = breed;
    }
}
