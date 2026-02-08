package com.demo.playground.test;

public class MaxAverageSubArray {

    public static void main(String[] args) {
        int[] arr = {2, 0, -1, 4, -4, 7, 1};
        int k = 3;
        int sum = 0;
        double average = 0;
        double maxAverage = 0;

        for(int i=0; i<k; i++) {
            sum = sum + arr[i];
        }
        maxAverage = (double)sum/k;

        for(int i = k; i< arr.length; i++) {
            sum = sum + arr[i] - arr[i - k];
            average = (double)sum/k;
            maxAverage = Math.max(average, maxAverage);
        }

        System.out.println("Max average " + maxAverage);

    }
}
