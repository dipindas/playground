package com.demo.playground.test3;

import io.micrometer.common.util.StringUtils;
import io.netty.util.internal.StringUtil;

import java.util.*;
import java.util.stream.Collectors;

public class IntergerGroping {

    public static void main(String[] args) {
        int[] arr = {101, 1001, 2000, 200, 303};

        Map<Integer, List<Integer>> result = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(
                x -> {
                    String str = String.valueOf(x);
                        Integer val = Integer.parseInt(str.substring(0, 1));
                        return val;
                }
        ));
        System.out.println(result);

        Map<Integer, List<Integer>> resultMap = new HashMap<>();
        for(int i : arr) {
            resultMap.computeIfAbsent(Integer.parseInt(String.valueOf(i).substring(0, 1)), k -> new ArrayList<>()).add(i);
        }
        System.out.println(resultMap);
    }
}
