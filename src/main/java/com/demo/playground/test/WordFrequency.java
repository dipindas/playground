package com.demo.playground.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequency {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("i can we can", "we can you can", "he can she can", "he can I can");
        Map<String, Long> wordMap = list.stream().map(x -> x.split(" "))
                .flatMap(x -> Arrays.stream(x))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(wordMap);
    }
}
