package com.demo.playground.util.collections;

import java.util.*;

public class TreeMapTry1 {

    public static void main(String[] args) {
        Employee e1 = new Employee("Ram", 123);
        Employee e2 = new Employee("Sam", 456);
        Employee e3 = new Employee("Ann", 789);

        Map<Employee, Integer> employeeMap = new TreeMap<>();
        employeeMap.put(e1, 10);
        employeeMap.put(e2, 30);
        employeeMap.put(e3, 20);

        for(Map.Entry map : employeeMap.entrySet()) {
            System.out.println(map.getKey() + " " + map.getValue());
        }

        /**
         * Comparator example
         */
        System.out.println("Using comparator");
        Employee2 e11 = new Employee2("Ram", 123);
        Employee2 e21 = new Employee2("Sam", 456);
        Employee2 e31 = new Employee2("Ann", 789);

        NameComparator nc = new NameComparator();
        Map<Employee2, Integer> employeeMap2 = new TreeMap<>(nc);
        employeeMap2.put(e11, 10);
        employeeMap2.put(e21, 30);
        employeeMap2.put(e31, 20);

        for(Map.Entry map : employeeMap2.entrySet()) {
            System.out.println(map.getKey() + " " + map.getValue());
        }
    }
}
class Employee implements Comparable<Employee> {
    private int rollNo;
    private String name;

    public Employee(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                '}';
    }


    @Override
    public int compareTo(Employee o) {
        //return Integer.compare(this.rollNo, o.getRollNo());
        if(this.getRollNo() > o.getRollNo()) return 1;
        else if (this.getRollNo() < o.getRollNo()) return -1;
        else return 0;
    }
}

class Employee2 {
    private int rollNo;
    private String name;

    public Employee2(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    @Override
    public String toString() {
        return "Employee2{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                '}';
    }

}

class NameComparator implements Comparator<Employee2> {

    @Override
    public int compare(Employee2 o1, Employee2 o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
