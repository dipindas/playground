package com.demo.playground.util.leet.binary;

public class FirstAndLastIndexSortedArray {

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 5, 5, 5, 5, 6, 7, 7, 7, 9, 9};
        int[] result = new int[2];
        int target = 9;

        result[0] = findLimits(arr, target, true);
        result[1] = findLimits(arr, target, false);

        System.out.println("Boundaries " + result[0] + " and " + result[1]);
    }

    private static int findLimits(int[] arr, int target, boolean isLeft) {
        int l = 0;
        int r = arr.length-1;
        int boundary = -1;

        while(l <= r) {
            int mid = l + (r - l)/2;
            if(arr[mid] == target) {
                boundary = mid;
                if(isLeft) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else if(arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return boundary;
    }
}
