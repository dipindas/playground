package com.demo.playground.util.leet.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class SubArraySum_560 {

    public static void main(String args[]) {
        int[] arr = {1, 4, 6, 2, 1, 3, 2};
                   //1, 5, 11, 13, 14, 17, 19
        int target = 5;
        int result = findSum(arr, target);
        System.out.println("Result: " + result);
    }

    private static int findSum(int[] arr, int target) {
        int result = 0;
        int preSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for(int i=0; i<arr.length; i++) {
            preSum = preSum + arr[i];
            if(map.containsKey(preSum - target)) {
                result = result + map.get(preSum - target);
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }

        return result;
    }
}
