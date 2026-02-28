package com.demo.playground.test3;

import java.util.HashMap;
import java.util.Map;

public class MaxSizeOfNonRepeatingChars {

    public static void main(String[] args) {
        String str = "abcbacd";
        char[] ch = str.toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for(int right = 0; right < ch.length; right++) {
            if(map.containsKey(ch[right])) {
                left = Math.max(left, map.getOrDefault(ch[right], 0) + 1);
            }
            map.put(ch[right], right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        System.out.println("Result " + maxLength);
    }

    public static class QuickSort {

        public static void main(String[] args) {
            int[] arr = {3, 5, 2, 1, 7, 5};
            int length = arr.length;
            quickSort(arr, 0, length-1);

            // print
            for(int i=0; i< arr.length; i++) System.out.println(" " + arr[i]);
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

            for(int j=left; j<right; j++) {
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
}
