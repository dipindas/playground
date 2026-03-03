package com.demo.playground.util.lowleveldesign.designpattern.creational;

public class BuilderPattern {

    // Required parameters
    private final String firstName;
    private final String lastName;

    // Optional parameters
    private final int age;
    private final String phone;
    private final String address;

    private BuilderPattern(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    @Override
    public String toString() {
        return "User: " + this.firstName + " " + this.lastName + ", " + this.age + ", " + this.phone + ", " + this.address;
    }

    public static class Builder {
        private final String firstName;
        private final String lastName;
        private int age;
        private String phone;
        private String address;

        public Builder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public BuilderPattern build() {
            return new BuilderPattern(this);
        }
    }

    public static void main(String[] args) {
        BuilderPattern user1 = new BuilderPattern.Builder("Lokesh", "Gupta")
                .age(30)
                .phone("1234567")
                .address("Fake address 1234")
                .build();

        System.out.println(user1);

        BuilderPattern user2 = new BuilderPattern.Builder("Jack", "Reacher")
                .age(40)
                .phone("5655")
                // no address
                .build();

        System.out.println(user2);

        BuilderPattern user3 = new BuilderPattern.Builder("Super", "Man")
                // No age
                // No phone
                // no address
                .build();

        System.out.println(user3);
    }
}
