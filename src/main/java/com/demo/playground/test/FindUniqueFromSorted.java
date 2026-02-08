package com.demo.playground.test;

public class FindUniqueFromSorted {
    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 1, 3, 3, 5, 6, 7};
        int length = arr.length;

        int left = 0;
        for(int right = 1; right < length; right++) {
            if(arr[left] != arr[right]) {
                left++;
                arr[left] = arr[right];
            }
        }

        System.out.println("Unique elements " + (left + 1));


        //for(int i=left+1; i< length; i++) arr[i] = -1;
        //for(Integer data : arr) System.out.println(data);
    }
}
