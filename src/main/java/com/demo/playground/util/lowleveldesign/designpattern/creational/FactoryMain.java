package com.demo.playground.util.lowleveldesign.designpattern.creational;

public class FactoryMain {
    /**
     * Delegating the object creation task to the Factory.
     *
     */
    public static void main(String[] args) {
        Vehicle vehicle = VehicleService.getTransaport("car");
        vehicle.transport();

        vehicle = VehicleService.getTransaport("bike");
        vehicle.transport();
    }
}

interface Vehicle {
    void transport();
}

class Car implements Vehicle {

    @Override
    public void transport() {
        System.out.println("Transport by car");
    }
}

class Bike implements Vehicle {

    @Override
    public void transport() {
        System.out.println("Transport by bike");
    }
}

class VehicleService {
    public static Vehicle getTransaport(String vehicle) {
        switch (vehicle) {
            case "car":
                return new Car();
            case "bike":
                return new Bike();
            default: throw new IllegalArgumentException();
        }
    }
}
