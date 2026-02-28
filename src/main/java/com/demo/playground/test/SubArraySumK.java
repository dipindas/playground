package com.demo.playground.test;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumK {
    public static void main(String[] args) {
        int[] arr = {2, 5, 6, 3, 4, 1};
                  // 2, 7, 13, 16, 20, 21
        int k = 7;
        int subArrayCount = 0;

        Map<Integer, Integer> preSumMap = new HashMap<>();
        int sum = 0;
        preSumMap.put(0, 1);

        for(int i = 0; i< arr.length; i++) {
            sum = sum + arr[i];
            if(preSumMap.containsKey(sum -k)) {
                subArrayCount = subArrayCount + preSumMap.getOrDefault(sum - k, 0);
            }

            preSumMap.put(sum, preSumMap.getOrDefault(sum, 0) + 1);
        }

        System.out.println("SubArray count " + subArrayCount);
    }
}
