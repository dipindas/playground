package com.demo.playground.test;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class StringManipulation1 {

    public static void main(String[] args) {
        String[] inputs = {
                "Service1-Feture1-Passed",
                "Service1-Feture2-Failed",
                "Service2-Feture1-Passed",
                "Service2-Feture2-Passed"
        };
        Map<String, Long> result = Arrays.asList(inputs).stream().map(x -> x.split("-"))
                .filter(arr -> arr[2].equalsIgnoreCase("Passed"))
                .collect(Collectors.groupingBy(arr -> arr[0], Collectors.counting()));
        System.out.println(result);
    }
}
