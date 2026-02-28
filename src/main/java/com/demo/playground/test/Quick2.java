package com.demo.playground.test;

public class Quick2 {

    public static void main(String[] args) {

        int[] arr = {2, 5, 3, 4, 1};
        int length = arr.length;
        int left = 0;
        int right = length-1;
        quickSort(arr, left, right);
        print(arr);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if(left < right) {
            int p = partition(arr, left, right);
            quickSort(arr, left, p-1);
            quickSort(arr, p+1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for(int j = left; j< right; j++) {
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

    private static void print(int [] arr) {
        for(int i=0; i< arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
