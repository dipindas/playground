package com.demo.playground.test2;

public class ReverseSpecialCharsOnly {
    public static void main(String[] args) {
        String str = "w@lm$rt";
        char[] charArray = str.toCharArray();

        int left = 0;
        int right = str.length() - 1;

        while(left < right) {
            if(Character.isLetter(charArray[left])) {
                left++;
            } else if(Character.isLetter(charArray[right])) {
                right--;
            } else {
                char temp = charArray[left];
                charArray[left] = charArray[right];
                charArray[right] = temp;

                left++;
                right--;
            }
        }
        System.out.println(String.valueOf(charArray));
    }
}
