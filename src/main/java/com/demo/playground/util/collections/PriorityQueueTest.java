package com.demo.playground.util.collections;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {

        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        pq1.add(1);
        pq1.add(5);
        pq1.add(8);
        pq1.add(2);
        pq1.add(3);
        System.out.println(pq1);

        Student st1 = new Student(100, "sam");
        Student st2 = new Student(500, "ram");
        Student st3 = new Student(200, "aam");
        Student st4 = new Student(700, "mam");
        Student st5 = new Student(400, "pam");
        RollNumberComparator rollNumberComparator = new RollNumberComparator();
        PriorityQueue<Student> pq2 = new PriorityQueue<>(rollNumberComparator);
        pq2.add(st1);
        pq2.add(st2);
        pq2.add(st3);
        pq2.add(st4);
        pq2.add(st5);
        System.out.println(pq2);

        System.out.println(pq2.peek());
        pq2.poll();
        System.out.println(pq2);
    }
}

class Student {

    private Integer rollNo;
    private String name;

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                '}';
    }

    public Student(Integer rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }
}

class RollNumberComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getRollNo() > o2.getRollNo() ? 1 : -1;
    }
}