package com.demo.playground.util.lowleveldesign.designpattern.creational;

public class UserBuilderSample {
    /**
     * Builder pattern separates out the object creation and its representation.
     * The object creation is done with the help of a static inner class called Builder
     * The class uses a private constructor which the Builder class uses to create an object of the class.
     *
     * Avoid constructor explosion
     * Enhance Readbility of constructors
     */

    // Mandatory parameters
    private String firstName;
    private String lastName;

    // Optional parameters
    private int age;
    private String address;

    private UserBuilderSample(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.address = builder.address;
    }

    @Override
    public String toString() {
        return "Builder{" +
                "firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' +
                ", age=" + this.age +
                ", address='" + this.address + '\'' +
                '}';
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private int age;
        private String address;

        // Constructors with mandatory fields
        public Builder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        // Optional parameter age
        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        // Optional parameter address
        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public UserBuilderSample build() {
            return new UserBuilderSample(this);
        }
    }

    public static void main(String[] args) {
        UserBuilderSample user1 = new UserBuilderSample.Builder("Dipin", "Das").setAddress("asdas").build();
        System.out.println(user1);
    }
}


