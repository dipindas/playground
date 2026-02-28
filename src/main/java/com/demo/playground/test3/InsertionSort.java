package com.demo.playground.test3;

public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 1, 7, 5};

        for(int i=1; i< arr.length; i++) {
            int j = i-1;
            int key = arr[i];

            while(j >=0 && key < arr[j]) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }

        for(int i=0; i< arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
