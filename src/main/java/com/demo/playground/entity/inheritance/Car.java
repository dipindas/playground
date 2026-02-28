package com.demo.playground.entity.inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("CAR")
@Getter
@Setter
public class Car extends Vehicle {
    private int numberOfDoors;
}
