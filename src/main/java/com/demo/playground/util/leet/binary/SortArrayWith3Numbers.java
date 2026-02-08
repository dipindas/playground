package com.demo.playground.util.leet.binary;

public class SortArrayWith3Numbers {

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 0, 0, 1, 2, 0};

        int low = 0;
        int mid = 0;
        int high = arr.length - 1;

        while(mid <= high) {
            switch(arr[mid]) {
                case 0:
                    swap(arr, low, mid);
                    low++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(arr, mid, high);
                    high--;
                    break;
            }
        }
        printArray(arr);

    }

    private static void printArray(int[] arr) {
        for(int i=0; i< arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
