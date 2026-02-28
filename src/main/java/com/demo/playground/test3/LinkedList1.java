package com.demo.playground.test3;

public class LinkedList1 {

    Node head;

    public LinkedList1() {
        this.head = head;
    }

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public void add(int data) {
        Node node = new Node(data);
        if(head == null) head = node;
        else {
            Node current = head;
            while(current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
    }

    public void delete(int data) {
        if(head == null) return;
        if(head.data== data) head = head.next;
        else {
            Node current = head;
            while(current.next != null && current.next.data != data) {
                current = current.next;
            }
            current.next = current.next.next;
        }
    }

    public void print() {
        Node current = head;
        while(current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    private void reverse() {
        Node current = head;
        Node prev = null;
        Node next = null;
        while(current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        this.head = prev;
    }

    public static void main(String[] args) {
        LinkedList1 ll1 = new LinkedList1();
        ll1.add(10);
        ll1.add(20);
        ll1.add(30);
        ll1.add(40);
        ll1.add(50);

        ll1.print();

//        System.out.println("Reversing");
//        ll1.reverse();
//        ll1.print();
//
//        System.out.println("Deleting 30");
//        ll1.delete(30);
//        ll1.print();
//        System.out.println("Deleting 50");
//        ll1.delete(50);
//        ll1.print();
//        System.out.println("Deleting 10");
//        ll1.delete(10);
//        ll1.print();

    }

}
