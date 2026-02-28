package com.demo.playground.test3;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {


        String[] str = {"van", "van", "van", "man", "ann", "ann", "man", "can"};

        Map<String, Long> sortedMap = Arrays.asList(str).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().thenComparing(Map.Entry.comparingByKey()).reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (old, newVal) -> newVal, LinkedHashMap::new));
        System.out.println("Result map ");
        for(Map.Entry<String, Long> map : sortedMap.entrySet()) {
            System.out.println("key " + map.getKey() + " value " + map.getValue());
        }



        String[] str1 = {"van", "van", "van", "man", "ann", "ann", "man", "can"};
        Map<String, Long> freqMap = Arrays.asList(str1).stream().sorted().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(freqMap);
    }
}
