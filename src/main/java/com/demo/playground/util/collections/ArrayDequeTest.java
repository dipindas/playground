package com.demo.playground.util.collections;

import java.util.ArrayDeque;

public class ArrayDequeTest {

    public static void main(String[] args) {

        /**
         * Queue implementation
         */
        ArrayDeque<String> arrayDeque = new ArrayDeque();
        arrayDeque.add("a");
        arrayDeque.add("b");
        arrayDeque.add("c");
        //arrayDeque.add(null);
        System.out.println(arrayDeque);

        arrayDeque.pop();
        System.out.println(arrayDeque);

        /**
         * Stack implementation
         */
        ArrayDeque<String> arrayDeque1 = new ArrayDeque();
        arrayDeque1.push("d");
        arrayDeque1.push("e");
        arrayDeque1.push("f");
        //arrayDeque1.add(null);
        System.out.println(arrayDeque1);

        arrayDeque1.pop();
        System.out.println(arrayDeque1);
    }
}
