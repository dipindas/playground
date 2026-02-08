package com.demo.playground.util.leet.twopointers;

public class ReverseOnlyCharacters {
    public static void main(String[] args) {
        String input = "w@lm@rt";
        char[] charArray = input.toCharArray();
        int length = charArray.length;

        int left = 0;
        int right = length - 1;

        while(left < right) {
            if(!Character.isLetter(charArray[left])) {
                left++;
            } else if(!Character.isLetter(charArray[right])) {
                right--;
            } else {
                char temp = charArray[left];
                charArray[left] = charArray[right];
                charArray[right] = temp;

                left++;
                right--;
            }
        }

        System.out.println("Final String " + new String(charArray));
    }
}
