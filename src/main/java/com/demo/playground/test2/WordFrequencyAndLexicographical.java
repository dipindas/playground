package com.demo.playground.test2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyAndLexicographical {
    public static void main(String[] args) {
        String[] arr = {"can", "man", "van", "can", "van", "ann", "ann"};
        Map<String, Long> stringMap = Arrays.asList(arr).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (old, newVal) -> newVal, LinkedHashMap::new));
        System.out.println("Result " + stringMap);
    }
}
