package com.demo.playground.test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostOccuringTest {
    public static void main(String[] args) {
        String name = "developero";

        Map<Character, Long> charMap = name.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> newVal,
                        LinkedHashMap::new
                ));
        for(Map.Entry<Character, Long> map : charMap.entrySet()) {
            System.out.println("key " + map.getKey() + " value " + map.getValue());
        }

        char ch = name.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.<Character, Long>comparingByValue().reversed()).findFirst().get().getKey();
        System.out.println("Most occurred character " + ch);
    }
}
