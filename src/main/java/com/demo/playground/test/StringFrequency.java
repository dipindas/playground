package com.demo.playground.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringFrequency {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("sam", "ram", "tom", "sam");
        Map<String, Long> countMap = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(countMap);
    }
}
