package com.demo.playground.test;

public class SortBinaryArray {

    public static void main(String[] args) {

        int[] arr = {1, 1, 0, 1, 0, 0, 0 , 1};
        int length = arr.length;

        int left = 0;
        for(int right = 0; right < length; right++) {
            if(arr[right] != 1) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
            }
        }

        System.out.println("Number of zeros " + left);
        System.out.println("===========");
        for(Integer num : arr) {
            System.out.println(num);
        }
    }
}
