package com.demo.playground.test2;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {

    /**
     * When a hashset stores value for first time, return true value and when a duplicate entry is added, returns false.
     *
     * @param args
     */
    public static void main(String[] args) {

        Set<Integer> set = new HashSet<>();
        int[] arr = {1, 2, 2, 3, 4, 1};
        for(int i=0; i< arr.length; i++) {
            System.out.println("Inserting " + arr[i] + " with result " + set.add(arr[i]));
        }
    }
}
