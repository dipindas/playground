package com.demo.playground.util.stream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapSortByKeyAndValue {

    public static void main(String[] args) {

        /**
         * Sort the words by its frequency.
         * If more than one word has same frequency, lexicographically arrange the words as well.
         * Solve the problem using java stream api and store the result in a hash map
         * Output:
         * can 1
         * ann 2
         * man 2
         * van 3
         */
        String[] str = {"van", "van", "van", "man", "ann", "ann", "man", "can"};

        Map<String, Long> sortedMap = Arrays.asList(str).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (old, newVal) -> newVal, LinkedHashMap::new));
        System.out.println("Result map ");
        for(Map.Entry<String, Long> map : sortedMap.entrySet()) {
            System.out.println(map.getKey() + "  " + map.getValue());
        }

        Integer a = null;
        Integer b = null;

        System.out.println(a == b); // returns true

        Map<String, Integer> testMap = new HashMap<>();
        System.out.println(testMap.get("abc"));


    }
}
