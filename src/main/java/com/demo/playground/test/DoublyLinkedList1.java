package com.demo.playground.test;

public class DoublyLinkedList1 {
    static class Node {
        Integer data;
        Node next;
        Node previous;
        public Node(int data) {
            this.data = data;
        }

    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.previous = head;
        head.next.next = new Node(30);
        head.next.next.previous = head.next;
        head.next.next.next = new Node(40);
        head.next.next.next.previous = head.next.next;
        head.next.next.next.next = new Node(50);
        head.next.next.next.next.previous = head.next.next.next;

        Node current = head;
        traverse(current);

        System.out.println("After reversing");

        Node node = reverse(current);
        traverse(node);
    }

    private static void traverse(Node node) {
        while(node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    private static Node reverse(Node current) { // 1 -> 2 -> 3
        Node head = current;
        Node temp = null;
        while(head != null) {
            temp = head.next;
            head.next = head.previous;
            head.previous = temp;

            current = head;
            head = head.previous;
        }
        return current;
    }
}
