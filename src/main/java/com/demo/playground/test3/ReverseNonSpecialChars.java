package com.demo.playground.test3;

public class ReverseNonSpecialChars {

    public static void main(String[] args) {


        String str = "w@lm$rt";
        int i=0, j=str.length()-1;
        char[] charArray = str.toCharArray();
        while(i < j) {
            while(i<j && !Character.isLetter(charArray[i])) {
                i++;
            }
            while(i < j && !Character.isLetter(charArray[j])) {
                j--;
            }
            swap(charArray, i, j);
            i++; j--;
        }
        print(charArray);
    }

    private static void print(char[] arr) {
        for(char ch : arr)
            System.out.println(" " + ch);
    }

    private static void swap(char[] arr, int first, int second) {
        char temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
