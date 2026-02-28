package com.demo.playground.test2;

public class ReverseLettersAndSpecChars {

    public static void main(String[] args) {
        /**
         * Input: s = ")ebc#da@f("
         * Output: "(fad@cb#e)"
         */

        String str = ")ebc#da@f(";
        char[] ch = str.toCharArray();
        int left = 0;
        int right = str.length()-1;

        StringBuilder letter = new StringBuilder();
        StringBuilder special = new StringBuilder();
        char[] result = new char[str.length()];

        for(int i=0; i< ch.length; i++) {
            if(Character.isLetter(ch[i])) {
                letter.append(ch[i]);
            }
        }
        letter.reverse();

        for(int i = 0; i< ch.length; i++) {
            if(!Character.isLetter(ch[i])) {
                special.append(ch[i]);
            }
        }
        special.reverse();

        int letterIndex = 0;
        int specialIndex = 0;
        for(int i= 0; i< str.length(); i++) {
            if(Character.isLetter(ch[i])) {
                result[i] = letter.charAt(letterIndex++);
            } else {
                result[i] = special.charAt(specialIndex++);
            }
        }

        System.out.println("Result " + String.valueOf(result));
    }
}
