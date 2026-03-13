package com.demo.playground.util.leet.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KDiff {
    public static void main(String[] args) {
        int[] arr = {4, 1, 1, 5, 3};
        int targetDiff = 2;
        Arrays.sort(arr); // sorting array
        int left = 0, right = 1, count = 0, length = arr.length;
        List<List<Integer>> result = new ArrayList<>();
        while(right < length) {
            int diff = arr[right] - arr[left];
            if(left >= right || diff < targetDiff) {
                right++;
            }
            if(diff > targetDiff) {
                left++;
            }
            else {
                List<Integer> current = new ArrayList<>();
                current.add(arr[left]);
                current.add(arr[right]);
                left ++;
                right++;
                count++;
                result.add(current);
                while(left < length && arr[left] == arr[left+1]) left++; // To avoid duplicates
                while(right < length && arr[right] == arr[right - 1]) right ++;
            }
        }
        System.out.println(" Total count " + count + " Result = " + result);
    }
}
