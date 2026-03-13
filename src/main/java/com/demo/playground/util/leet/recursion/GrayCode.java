package com.demo.playground.util.leet.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrayCode {
    /**
     * GrayCode is dogits with changes in a single digit compared to the previous value.
     *
     * n = 1
     * 0
     * 1
     *
     * n = 2
     *
     * 0 0
     * 0 1
     * 1 1
     * 1 0
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> output = greyCode(3);
        System.out.println(output);
    }

    private static List<String> greyCode(int n) {
        List<String> result = new ArrayList<>();
        if(n == 0) return new ArrayList<>();
        if(n == 1) return new ArrayList<>(Arrays.asList("0", "1"));
        else {
            List<String> prev = greyCode(n - 1);
            for(String str : prev) {
                result.add("0 " + str);
            }
            /**
             * This loop is from the opposite side so that we get binary with change in a single digit.
             */
            for(int i=prev.size() - 1; i>=0; i--) {
                result.add("1 " + prev.get(i));
            }
            return result;
        }
    }
}
