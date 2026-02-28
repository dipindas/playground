package com.demo.playground.test;

public class DoublyLinkedList2 {

    Node head;

    public DoublyLinkedList2() {
        this.head = null;
    }

    static class Node {
        private int data;
        private Node next;
        private Node prev;

        public Node(int data) {
            this.data = data;
        }
    }

    private void add(int data) {
        if(head == null) {
            head = new Node(data);
        } else {
            Node current = head;
            while(current.next != null) {
                current = current.next;
            }
            Node newNode = new Node(data);
            current.next = newNode;
            newNode.prev = current;
        }
    }

    private void print() {
        Node current = head;
        while(current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    private void reverse() {
        Node temp = null;
        Node current = head;
        while(current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;

            current = current.prev;

        }
        if(temp != null) {
            head = temp.prev;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList2 ll2 = new DoublyLinkedList2();
        ll2.add(10);
        ll2.add(20);
        ll2.add(30);

        ll2.print();

        ll2.reverse();

        ll2.print();
    }
}
