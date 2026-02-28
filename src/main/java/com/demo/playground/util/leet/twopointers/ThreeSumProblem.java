package com.demo.playground.util.leet.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumProblem {
    public static void main(String[] args) {
        int[] arr = {2, 1, 3, 6, 2, 1, 3, 5, 3, 7};
        int target = 10;
        List<List<Integer>> result = new ArrayList<>();
        int length = arr.length;
        int sum = 0;
        Arrays.sort(arr);

        for(int i = 0; i< length; i++) {
            if(i > 0 && arr[i] == arr[i-1]) continue;

            int current = arr[i];
            int left = i+1;
            int right = length-1;
            while(left < right) {
                sum = current + arr[left] + arr[right];
                if(sum == target) {
                    List<Integer> partialOutput = new ArrayList<>();
                    partialOutput.add(current);
                    partialOutput.add(arr[left]);
                    partialOutput.add(arr[right]);
                    result.add(partialOutput);

                    left++;
                    right --;

                    while(left < right && arr[left] == arr[left - 1]) left++;
                    while(left < right && arr[right] == arr[right + 1]) right--;

                } else if(sum < target) left++;
                else right--;
            }

        }
        System.out.println("Output " + result);
    }
}
