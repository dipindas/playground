package com.demo.playground.test;

public class InsretionSort {
    public static void main(String[] args) {

        int arr[] = {3, 2, 5, 7, 6, 1};

        for(int i = 1; i<arr.length; i++) {
            int key = arr[i];

            int j = i-1;
            while(j >= 0 && key < arr[j]) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }

        for(int i = 0; i< arr.length; i++)
            System.out.println(arr[i]);
    }
}
