package com.demo.playground.test;

import java.util.ArrayList;
import java.util.List;

public class TwoSumSortedArray {
    public static void main(String[] args) {

        int[] arr = {2, 4, 6, 7, 8};
        int target = 10;
        int length = arr.length;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> partialResult;

        int left = 0;
        int right = length - 1;

        while(left < right) {
            if(arr[left] + arr[right] == target) {
                partialResult = new ArrayList<>();
                partialResult.add(arr[left]);
                partialResult.add(arr[right]);
                result.add(partialResult);
            }
            if(arr[left] + arr[right] < target) left++;
            else right--;
        }

        System.out.println(result);
    }
}
