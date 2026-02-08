package com.demo.playground.test;

public class SecondLargestNumber {

    public static void main(String[] args) {
        int[] arr = {1};//{2, 5, 7, 3, 4,  8};
        int length = arr.length;
        int largest = 0;
        int secondLargest = 0;

        for(int i =0; i< length; i++) {
            if(arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            } else if(arr[i] > secondLargest && largest > arr[i]) {
                secondLargest = arr[i];
            }

        }
        System.out.println("Second largest " + secondLargest);
    }
}
