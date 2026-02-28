package com.demo.playground.test3;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {4,2,3,1,6,5};
        int left = 0;
        int right = arr.length-1;
        quickSort(arr, left, right);
        print(arr);
    }

    private static void print(int[] arr) {
        for(int ar : arr) System.out.print(" " + ar);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if(left < right) {
            int p = findPartition(arr, left, right);
            quickSort(arr, p + 1, right);
            quickSort(arr, left, p - 1);
        }
    }

    private static int findPartition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;

        for(int j=left; j< right; j++) {
            if(arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        int temp = arr[i];
        arr[i] = arr[right];
        arr[right] = temp;

        return i;
    }
}
