package com.demo.playground.test;

import org.apache.logging.log4j.util.PropertySource;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostOccuringCharacter {

    public static void main1(String[] args) {
        String name = "developer";
        java.lang.Character result = name.chars().mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new))
                .entrySet().stream().findFirst().get().getKey();
        System.out.println("Most occurring character " + result);

    }

    public static void main(String[] args) {
        String name = "developer";
        Map<Character, Long> frequencyMap = new HashMap<>();

        for(char ch : name.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0L) + 1);
        }

        long maxVal = -1;
        char result='0';
        for(Map.Entry<Character, Long> map : frequencyMap.entrySet()) {
            if(map.getValue() > maxVal) {
                maxVal = map.getValue();
                result = map.getKey();
            }
        }
        System.out.println("Most occuring word " + String.valueOf(result));


        Map<Character, Long> charMap = name.chars().parallel().mapToObj(x -> (char)x)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Frequency count using parallel" + charMap);

    }
}
