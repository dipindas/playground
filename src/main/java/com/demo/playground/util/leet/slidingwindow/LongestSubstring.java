package com.demo.playground.util.leet.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {
    public static void main(String[] args) {
        String str = "abba";
        Map<Character, Integer> map = new HashMap<>();
        int longestSubstring = 0;
        int left = 0;
        int length = str.length();

        for(int right = 0; right < length; right++) {
            Character current = str.charAt(right);
            if(map.containsKey(current)) {
                left = Math.max(left, map.get(current) + 1);
            }
            map.put(current, right);
            longestSubstring = Math.max(longestSubstring, right - left + 1);
        }

        System.out.println("Longest substring " + longestSubstring);
    }
}
