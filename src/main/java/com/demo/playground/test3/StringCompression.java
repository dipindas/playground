package com.demo.playground.test3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringCompression {
    public static void main(String[] args) {
        String str = "aaabbcd";
        // output -> a3b2c1d1

//        Map<Character, Long> strMap = str.chars().mapToObj(c -> (char) c)
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//        StringBuilder stringBuilder = new StringBuilder();
//        for(Map.Entry map : strMap.entrySet()) {
//            stringBuilder.append(map.getKey()).append(map.getValue());
//        }
//        System.out.println(stringBuilder.toString());

        int left = 0, right = 0, count = 1;
        int length = str.length();
        char[] arr = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<length; i++) {
            if(i+1 < length && arr[i] == arr[i+1]) {
                count++;
            } else {
                stringBuilder.append(arr[i]).append(count);
                count = 1;
            }
        }
        System.out.println(stringBuilder);
    }
}
