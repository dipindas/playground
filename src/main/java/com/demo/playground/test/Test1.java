package com.demo.playground.test;

import java.util.stream.Collectors;

public class Test1 {

    /**
     * Program to replace i with q in java streams
     * @param args
     */
    public static void main(String[] args) {
        String name = "microservice";

        name = name.chars().map(c -> c == 'i' ? 'q' : c)
                .mapToObj(c -> String.valueOf((char)c)).collect(Collectors.joining());
        System.out.println(name);
    }
}
