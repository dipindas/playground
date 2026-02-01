package com.demo.playground.test;

public class MergeSort {

    public static void main(String args[]) {
        int[] arr = {20, 50, 10, 40,30};
        int length = arr.length;
        int left = 0;
        int right = length - 1;
        mergeSort(left, right, arr);
        System.out.println("Sorted array: ");
        for (int i=0; i< length; i++) {
            System.out.print(" " + arr[i]);
        }
    }

    private static void mergeSort(int left, int right, int[] arr) {
        if(left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(left, mid, arr);
            mergeSort(mid+1, right, arr);
            merge(left, right, mid, arr);
        }
    }

    private static void merge(int left, int right, int mid, int[] arr) {
        int leftArraySize = mid - left +1;
        int rightArraySize = right - mid;

        int leftArray[] = new int[leftArraySize];
        int rightArray[] = new int[rightArraySize];

        for(int i = 0; i< leftArraySize; i++) {
            leftArray[i] = arr[left+i];
        }

        for(int i = 0; i< rightArraySize; i++) {
            rightArray[i] = arr[mid+1+i];
        }

        int i = 0, j = 0, k = left;
        while(i < leftArraySize && j < rightArraySize) {
            if(leftArray[i] < rightArray[j]) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
            }
        }

        while(i < leftArraySize) {
            arr[k++] = leftArray[i++];
        }

        while(j < rightArraySize) {
            arr[k++] = rightArray[j++];
        }
    }
}
