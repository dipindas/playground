package com.demo.playground.test;

public class LinkedList2 {

    Node head;

    public LinkedList2() {
        this.head = null;
    }

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public void add(int data) {
        if(head == null) {
            head = new Node(data);
        } else {
            Node current = head;
            while(current.next != null) {
                current = current.next;
            }
            current. next = new Node(data);
        }
    }

    private void reverse() {
        Node current = head;
        Node prev = null;
        Node next = null;

        while(current!= null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        this.head = prev;
    }

    public void print() {
        Node current = head;
        while(current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public static void main(String[] args) {

        LinkedList2 ll2 = new LinkedList2();
        ll2.add(10);
        ll2.add(20);
        ll2.add(30);
        ll2.add(40);
        ll2.add(50);

        ll2.print();
        ll2.reverse();
        System.out.println("After reversal");
        ll2.print();
    }


}
