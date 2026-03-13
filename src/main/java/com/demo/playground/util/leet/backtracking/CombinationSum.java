package com.demo.playground.util.leet.backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), arr, target, 0);
        System.out.println(result);
    }

    private static void helper(List<List<Integer>> result, List<Integer> current, int[] arr, int target, int index) {
        if(target == 0) {
            result.add(new ArrayList<>(current));
        }
        if(target < 0) return;

        for(int i=index; i< arr.length; i++) {
            current.add(arr[i]);
            helper(result, current, arr, target - arr[i], i);
            current.remove(current.size() - 1);
        }
    }
}
