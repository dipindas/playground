package com.demo.playground.util.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Exercise on
 * computeIfAbsent(), putIfAbsent()
 * computeIfPresent()
 * getOrDefault()
 *
 */
public class MapSpecialFunctions {
    public static void main(String[] args) {
        Map<String, Integer> testMap1 = new HashMap<>();
        testMap1.put("def", 456);
        testMap1.put("abc", 567);

        System.out.println("Initial Map: " + testMap1);

        // ----------------------------------------------------------------
        // 1. getOrDefault(key, defaultValue)
        // Returns the value if key exists, otherwise returns defaultValue.
        // Does NOT modify the map.
        // ----------------------------------------------------------------
        System.out.println("\n--- getOrDefault ---");
        Integer val1 = testMap1.getOrDefault("abc", 0); // Exists, returns 567
        Integer val2 = testMap1.getOrDefault("xyz", 0); // Missing, returns 0
        System.out.println("Value for 'abc': " + val1);
        System.out.println("Value for 'xyz': " + val2);

        /*
         * PROBLEM 1:
         * Given a map of student names to scores, print the score for "Alice".
         * If "Alice" is not in the map, print -1.
         */


        // ----------------------------------------------------------------
        // 2. putIfAbsent(key, value)
        // Puts the value ONLY if the key is NOT present or mapped to null.
        // Returns the previous value (null if key was missing).
        // ----------------------------------------------------------------
        System.out.println("\n--- putIfAbsent ---");
        testMap1.putIfAbsent("abc", 999); // "abc" exists, does nothing. Returns 567.
        testMap1.putIfAbsent("ghi", 789); // "ghi" missing, adds it. Returns null.
        System.out.println("Map after putIfAbsent: " + testMap1);

        /*
         * PROBLEM 2:
         * You are initializing a configuration map.
         * Ensure that the key "timeout" is set to 5000 ONLY if it hasn't been set already.
         */


        // ----------------------------------------------------------------
        // 3. computeIfAbsent(key, mappingFunction)
        // If key is missing, computes the value using the function and adds it.
        // If key exists, does nothing and returns existing value.
        // Great for building lists/maps as values.
        // ----------------------------------------------------------------
        System.out.println("\n--- computeIfAbsent ---");
        Map<String, java.util.List<String>> groups = new HashMap<>();
        
        // "fruit" is missing. Creates new ArrayList, puts it in map, then adds "apple".
        groups.computeIfAbsent("fruit", k -> new java.util.ArrayList<>()).add("apple");
        
        // "fruit" exists. Returns existing ArrayList, then adds "banana".
        groups.computeIfAbsent("fruit", k -> new java.util.ArrayList<>()).add("banana");
        
        System.out.println("Groups: " + groups);

        /*
         * PROBLEM 3:
         * Given a list of words ["apple", "banana", "apricot", "blueberry"],
         * group them by their first letter using computeIfAbsent.
         * Result should be: {a=[apple, apricot], b=[banana, blueberry]}
         */
        List<String> words = List.of("apple", "banana", "apricot", "blueberry");
        Map<String, List<String>> fruitMap2 = words.stream().collect(Collectors.groupingBy(x -> x.substring(0, 1)));
        System.out.println(fruitMap2);

        Map<String, List<String>> fruitMap3 = new HashMap<>();
        for(String str : words) {
            fruitMap3.computeIfAbsent(str.substring(0, 1), v -> new ArrayList<>()).add(str);
        }
        System.out.println("fruitMap3 " + fruitMap3);



        // ----------------------------------------------------------------
        // 4. computeIfPresent(key, remappingFunction)
        // If key exists and is non-null, computes a new value.
        // If function returns null, the key is REMOVED.
        // ----------------------------------------------------------------
        System.out.println("\n--- computeIfPresent ---");
        testMap1.computeIfPresent("abc", (k, v) -> v + 10); // "abc" exists (567), becomes 577
        testMap1.computeIfPresent("xyz", (k, v) -> v + 10); // "xyz" missing, does nothing
        System.out.println("Map after computeIfPresent: " + testMap1);

        /*
         * PROBLEM 4:
         * You have a map of inventory counts: {"apple": 10, "banana": 5}.
         * Decrease the count of "apple" by 1 only if it exists.
         */

        Map<String, Integer> fruitInventory = new HashMap<>();
        fruitInventory.put("apple", 10);
        fruitInventory.put("banana", 5);
        fruitInventory.computeIfPresent("apple", (k, v) -> v - 1);
        fruitInventory.computeIfPresent("apple", (k, v) -> v - 1);
        System.out.println(fruitInventory);
    }
}
