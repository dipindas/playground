package com.demo.playground.test;

import java.util.*;
import java.util.stream.Collectors;

public class RemoveDuplicateObjects {
    public static void main(String[] args) {
        Customer customer1 = new Customer(1, "Ram", 25);
        Customer customer2 = new Customer(1, "Sam", 28);
        Customer customer3 = new Customer(1, "Ram", 30);
        Customer customer4 = new Customer(2, "Tom", 25);

        Set<Customer> set = new HashSet<>();
        set.add(customer1);
        set.add(customer2);
        set.add(customer3);
        set.add(customer4);

        System.out.println("Customers with unique id and names are ");
        for(Customer var : set) {
            System.out.println(var);
        }

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        customerList.add(customer4);
        List<Customer> uniqueCustomers = customerList.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Using stream, unique objects " + uniqueCustomers);
    }
}

class Customer {
    private int id;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Customer(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Customer customer)) return false;
        return id == customer.id && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



}
