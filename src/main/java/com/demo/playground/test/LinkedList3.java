package com.demo.playground.test;

public class LinkedList3 {

    Node head;

    public LinkedList3() {
        this.head = null;
    }

    static class Node{
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }

    }

    private void add(int data) {
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

    private void reverse() {
        Node current = head;
        Node next = null;
        Node prev = null;

        while(current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        this.head = prev;
    }

    private void print() {
        Node current = head;
        while(current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    private void delete(int data) {
        if(head == null) return;
        else if(head.data == data) head = head.next;
        else {
            Node current = head;
            while(current.next != null && current.next.data != data) {
                current = current.next;
            }
            current.next = current.next.next;
        }
    }

    public static void main(String[] args) {
        LinkedList3 ll3 = new LinkedList3();

        ll3.add(10);
        ll3.add(20);
        ll3.add(30);
        ll3.add(40);
        ll3.add(50);
        ll3.print();

        System.out.println("After reversal ");

        ll3.reverse();
        ll3.print();

        System.out.println("Deleting 50");
        ll3.delete(50);
        ll3.print();

        System.out.println("Deleting 10");
        ll3.delete(10);
        ll3.print();


    }
}
