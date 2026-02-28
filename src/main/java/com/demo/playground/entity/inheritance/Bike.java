package com.demo.playground.entity.inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("BIKE")
@Getter
@Setter
public class Bike extends Vehicle {
    private boolean hasSidecar;
}
