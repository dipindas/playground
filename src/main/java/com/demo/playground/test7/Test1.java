package com.demo.playground.test7;

import java.util.*;
import java.util.stream.Collectors;

public class Test1 {

    public static void main(String[] args) {
        String[] arr = {"eat","tea","tan","ate","nat","bat"};
        List<String> stringList = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(String str : arr) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String str1 = String.valueOf(charArray);
            stringList.add(str1);
            if(!map.containsKey(str1)) {
                List<String> strList = new ArrayList<>();
                strList.add(str);
                map.put(str1, strList);
            } else {
                List<String> strList = map.get(str1);
                strList.add(str);
            }
        }
        List<List<String>> result = map.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());
        System.out.println(result);
    }
}
