package com.demo.playground.test2;

import java.util.HashMap;
import java.util.Map;

public class MaxCountOfNonRepeatingChars {

    public static void main(String[] args) {
        char[] ch = {'a', 'b', 'c', 'd', 'e','c', 'b', 'f', 'g'};
        Map<Character, Integer> charMap = new HashMap<>();
        int currentMax = 0;

        int left = 0;
        for(int right = 0; right < ch.length; right++) {
            if(charMap.containsKey(ch[right])) {
                left = Math.max(left, charMap.getOrDefault(ch[right], 0) + 1);
            }
            charMap.put(ch[right], right);
            currentMax = Math.max(currentMax, right - left + 1);
        }
        System.out.println("Largest unique elements " + currentMax);
    }
}
