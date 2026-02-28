package com.demo.playground.test3;

public class LinkedList2 {

    Node head;

    static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public LinkedList2() {
        this.head = null;
    }

    private void add(int data) {
        Node node = new Node(data);
        if(head == null){
            head = node;
        } else {
            Node current = head;
            while(current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
    }

    private void delete(int data) {
        if(head == null) return;
        else if(head.data == data) head = null;
        else{
            Node current = head;
            while(current.next != null && current.next.data != data) {
                current = current.next;
            }
            if(current.next != null && current.next.data == data) {
                current.next = current.next.next;
            }
        }
    }

    private void print() {
        Node current = head;
        System.out.println();
        while(current != null) {
            System.out.print(current.data + " ");
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
        head = prev;
    }

    public static void main(String[] args) {

        LinkedList2 ll2 = new LinkedList2();
        ll2.add(10);
        ll2.add(20);
        ll2.add(30);
        ll2.add(40);
        ll2.add(50);
        ll2.print();

        System.out.println("Removing 30");
        ll2.delete(30);
        ll2.print();

        System.out.println("Reversing LL");
        ll2.reverse();
        ll2.print();
    }
}
