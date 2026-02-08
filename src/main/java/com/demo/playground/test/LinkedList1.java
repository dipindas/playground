package com.demo.playground.test;


public class LinkedList1 {
    Node head;

    public LinkedList1() {
        this.head = null;
    }

    static class Node {
        private Integer data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }
    public static void main(String[] args) {
//        Node head = new Node(10);
//        head.next = new Node(20);
//        head.next.next = new Node(30);
//        head.next.next.next = new Node(40);
//        head.next.next.next.next = new Node(50);



//        Node current = head;
//        traverse(current);
//
//        System.out.println("After reversing");
//
//        Node node = reverse(current);
//        traverse(node);


        LinkedList1 ll = new LinkedList1();
        ll.add(10);
        ll.add(20);
        ll.add(30);
        ll.add(40);
        ll.add(50);

        ll.traverse();
    }

    private void add(int data) {

        if(head == null) {
            head = new Node(data);
        } else {
            Node current = head;
            while(current.next != null) {
                current = current.next;
            }
            Node node = new Node(data);
            current.next = node;
        }
    }

    private void traverse() {
        while(head != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }

    private static void traverse(Node current) {
        while(current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    private static Node reverse(Node current) {
        Node prev = null;
        Node next = null;
        while(current != null) { // 1 -> 2 -> 3
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}
