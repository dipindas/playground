package com.demo.playground.test3;

public class StringCompression2 {
    public static void main(String[] args) {
        String str = "aaabbcd";
        int count = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i< str.length(); i++) {
            if(i+1 < str.length() && str.charAt(i) == str.charAt(i+1)) {
                count++;
            } else{
                stringBuilder.append(str.charAt(i)).append(count);
                count = 1;
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
