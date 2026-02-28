package com.demo.playground.test;

public class MergeSort2 {
    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 3, 6, 5};
        int length = arr.length;
        int l = 0, r = length-1;
        mergeSort(arr, l, r);
        printArray(arr);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if(l < r) {
            int mid = l + (r - l)/2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid+1, r);
            merge(arr, l , r, mid);
        }
    }

    private static void merge(int[] arr, int left, int right, int mid) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for(int i = 0; i< n1; i++) {
            leftArray[i] = arr[left + i];
        }

        for(int j = 0; j<n2; j++) {
            rightArray[j] = arr[mid+1+j];
        }

        int i=0, j=0, k=left;
        while(i<n1 && j<n2) {
            if(leftArray[i] < rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while(i<n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while(j<n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    private static void printArray(int[] arr) {
        for(int i=0; i< arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
