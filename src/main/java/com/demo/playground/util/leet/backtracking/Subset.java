package com.demo.playground.util.leet.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subset {
    public static void main(String[] args) {
        int[] arr = {3, 5, 6};
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), 0, arr);
        System.out.println(result);
    }

    private static void helper(List<List<Integer>> result, List<Integer> current, int index, int[] arr) {
        result.add(new ArrayList<>(current));
        for(int i=index; i< arr.length; i++) {
            current.add(arr[i]);
            helper(result, current, i+1, arr);
            current.remove(current.size()-1);
        }
    }

}
