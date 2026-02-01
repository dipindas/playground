package com.demo.playground.util.leet;

import java.util.Arrays;

public class PushAllZerosToLast_283 {
    public static void main(String args[]) {
        int[] arr = {1, 9, 8, 4, 0, 0, 2};
        int length = arr.length;
        int currentZero = 0;

        for(int i=0; i<length; i++) {
            if(arr[i] != 0) {
                int temp = arr[i];
                arr[i] = arr[currentZero];
                arr[currentZero++] = temp;
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}

