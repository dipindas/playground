package com.demo.playground.test;

public class QuickSort {
    // 3, 2, 4, 8, 5, 6
    public static void main(String[] args) {
        int[] arr = {4, 2, 6, 1, 3, 5};
        int length = arr.length;
        int left = 0;
        int right = length-1;
        performQuickSort(arr, left, right);
        print(arr);
    }

    private static void performQuickSort(int[] arr, int left, int right) {
        if(left < right) {
            int p = partition(arr, left, right);

            performQuickSort(arr, left, p-1);
            performQuickSort(arr, p+1, right);
        }
    }

    private static int partition(int arr[], int left, int right) {
        int pivot = arr[right];
        int i = left - 1;

        for(int j=left; j< right; j++) {
            if(arr[j] <= pivot) {
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

    private static void print(int[] arr) {
        for(int i=0; i< arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
