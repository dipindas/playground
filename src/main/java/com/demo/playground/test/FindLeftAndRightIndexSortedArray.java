package com.demo.playground.test;

public class FindLeftAndRightIndexSortedArray {

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 4, 4, 4, 6, 6, 6, 7, 7, 7, 7};
        int length = arr.length;
        int target = 7;
        int result[] = new int[2];
        int left = 0;
        int right = length - 1;
        result[0] = findLeftIndex(arr, left, right, target);
        System.out.println("Left index of " + target + " is " + result[0]);


    }

    private static int findLeftIndex(int[] arr, int left, int right, int target) {
        int boundary = -1;
        while(left <= right) {
            int mid = left + (right - left)/2;
            if(arr[mid] == target) {
                boundary = mid;
                right = mid - 1;
            } else if(target < mid) {
                right = mid - 1;
            } else {
                left = mid+1;
            }
        }
        return boundary;
    }
}
