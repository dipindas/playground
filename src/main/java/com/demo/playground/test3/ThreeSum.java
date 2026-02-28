package com.demo.playground.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {

        int[] arr = {2, 1, 3, 4, 5, 3, 2, 1, 7};
        int target = 10;
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(arr);
        for(int i=0; i<arr.length; i++) {
            if(i>0 && arr[i] == arr[i-1])
                continue;
            int left = i+1;
            int right = arr.length-1;
            while(left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if(sum == target) {
                    List<Integer> partialResult = new ArrayList<>();
                    partialResult.add(arr[i]);
                    partialResult.add(arr[left]);
                    partialResult.add(arr[right]);
                    result.add(partialResult);

                    left++;
                    right--;

                    while(left< right && arr[left] == arr[left-1]) left++;
                    while(left < right && arr[right] == arr[right+1]) right--;

                } else if(sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        System.out.println("Result " + result);
    }
}
