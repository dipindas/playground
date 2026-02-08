package com.demo.playground.util.leet.twodarray;

public class RotateArrayByK {

    public static void main(String[] args) {

        int[][] arr = {
                {2, 4, 7, 5},
                {0 , 5, 1, 8},
                {1, 2, 3, 4}
        };
        int k = 2;
        int row = arr.length;
        int col = arr[0].length;

        k = k % col;
        if(k == col)
            return;

        /*
        1. Reverse enire columns
        2. Reverse 1 to K
        3. Reverse K+1 to col
         */

        for(int i=0; i< arr.length; i++) {
            reverse(arr[i], 0, col-1);
            reverse(arr[i], 0, k-1);
            reverse(arr[i], k, col-1);
        }
        print(arr);
    }

    private static void reverse(int[] arr, int start, int end) {
        while(start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }

    private static void print(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;

        for(int i=0; i<row; i++) {
            for(int j =0; j<col; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
