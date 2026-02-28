package com.demo.playground.test2;

import java.util.ArrayList;
import java.util.List;

public class TwoSumOnSortedArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 6, 7};
        int target = 7;
        int length = arr.length;
        List<List<Integer>> result = new ArrayList<>();

        int left = 0;
        int right = length - 1;

        while(left < right) {
            int sum = arr[left] + arr[right];
            if(sum == target) {
                List<Integer> partial = new ArrayList<>();
                partial.add(arr[left]);
                partial.add(arr[right]);
                result.add(partial);

                left++;
                right--;
            } else if(sum < target) left++;
            else right--;
        }

        System.out.println(result);
    }
}
