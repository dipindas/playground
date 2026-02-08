package com.demo.playground.util.leet.hashset;

import java.util.HashSet;
import java.util.Set;

public class FindUniqueFromUnsortedArray {
    public static void main(String[] args) {
        int[] arr = {2, 5, 3, 2, 8, 5, 7};
        int writeIndex = 0;
        Set<Integer> set = new HashSet<>();

        for(int i=0; i< arr.length; i++) {
            if(!set.contains(arr[i])) {
                set.add(arr[i]);
                arr[writeIndex] = arr[i];
                writeIndex++;
            }
        }
        System.out.println("Unique elements: " + writeIndex);
    }
}
