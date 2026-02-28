package com.demo.playground.test3;

public class FindSinglePeakElement {

    public static void main(String[] args) {

        int[] arr = {10, 3, 4, 2, 5, 7, 9};
        int left = 0;
        int right = arr.length-1;

        while(left < right) {
            int mid = left + (right - left)/2;
            if(arr[mid] < arr[mid+1]) {
                left = mid+1;
            } else {
                right = mid;
            }
        }
        System.out.println("Local peak " + arr[right]);
    }
}
