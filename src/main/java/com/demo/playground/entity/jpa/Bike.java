package com.demo.playground.entity.jpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("BIKE")
@Getter
@Setter
@NoArgsConstructor
public class Bike extends Vehicle {

    private boolean hasSidecar;

    public Bike(String manufacturer, boolean hasSidecar) {
        super(manufacturer);
        this.hasSidecar = hasSidecar;
    }
}
