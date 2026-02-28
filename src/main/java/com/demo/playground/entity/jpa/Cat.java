package com.demo.playground.entity.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jpa_cat")
@Getter
@Setter
@NoArgsConstructor
public class Cat extends Animal {

    private boolean isIndoor;

    public Cat(String species, boolean isIndoor) {
        super(species);
        this.isIndoor = isIndoor;
    }
}
