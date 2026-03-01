package com.demo.playground.entity.jpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("CAR")
@Getter
@Setter
@NoArgsConstructor
public class Car extends Vehicle {

    private int numberOfDoors;

    public Car(String manufacturer, int numberOfDoors) {
        super(manufacturer);
        this.numberOfDoors = numberOfDoors;
    }
}
