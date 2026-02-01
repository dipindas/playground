package com.demo.playground.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class StreamPractice {

    // Helper classes for practice questions
    static class Person {
        String name;
        int age;
        String country;
        String city;

        public Person(String name, int age) { this.name = name; this.age = age; }
        public Person(String name, String country) { this.name = name; this.country = country; }
        public Person(String name, String city, int age) { this.name = name; this.city = city; this.age = age; }
        public Person(int id, String name) { this.name = name; }

        @Override
        public String toString() { return name; }
    }

    static class Transaction {
        String date;
        int value;

        public Transaction(String date, int value) { this.date = date; this.value = value; }
    }

    static class Employee {
        String name;
        int salary;

        public Employee(String name, int salary) { this.name = name; this.salary = salary; }

        @Override
        public String toString() { return name + "(" + salary + ")"; }
    }

    public static void main(String args[]) {

        /*
           1. Iterate through a list of integers and print each value.
           Sample Input: Arrays.asList(1, 2, 3, 4, 5)
           Expected Output:
           1
           2
           3
           4
           5
        */

        System.out.println("Trying to solve first problem");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream().forEach(System.out::println);




        /*
           2. Filter out the even numbers from a list of integers and print them.
           Sample Input: Arrays.asList(1, 2, 3, 4, 5, 6)
           Expected Output: 2 4 6
        */

        /*
           3. Find the sum of all numbers in a list of integers.
           Sample Input: Arrays.asList(1, 2, 3, 4, 5)
           Expected Output: 15
        */

        List<Integer> num1 = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = num1.stream().reduce((a, b) -> a + b).get();
        System.out.println("Sum " + sum);
        /*
           4. Find the maximum element in a list of integers.
           Sample Input: Arrays.asList(1, 5, 3, 9, 2)
           Expected Output: 9
        */

        /*
           5. Count the number of strings in a list that start with a specific character (e.g., 'A').
           Sample Input: Arrays.asList("Apple", "Banana", "Avocado", "Cherry"), 'A'
           Expected Output: 2
        */

        /*
           6. Convert all strings in a list to uppercase.
           Sample Input: Arrays.asList("hello", "world")
           Expected Output: ["HELLO", "WORLD"]
        */

        /*
           7. Sort a list of strings in alphabetical order.
           Sample Input: Arrays.asList("Banana", "Apple", "Cherry")
           Expected Output: ["Apple", "Banana", "Cherry"]
        */

        /*
           8. Sort a list of strings in reverse alphabetical order.
           Sample Input: Arrays.asList("Banana", "Apple", "Cherry")
           Expected Output: ["Cherry", "Banana", "Apple"]
        */

        /*
           9. Find the distinct numbers in a list of integers.
           Sample Input: Arrays.asList(1, 2, 2, 3, 4, 4, 5)
           Expected Output: [1, 2, 3, 4, 5]
        */

        /*
           10. Count the number of distinct numbers in a list of integers.
           Sample Input: Arrays.asList(1, 2, 2, 3, 4, 4, 5)
           Expected Output: 5
        */

        /*
           11. Find the average of all numbers in a list of integers.
           Sample Input: Arrays.asList(1, 2, 3, 4, 5)
           Expected Output: 3.0
        */

        /*
           12. Find the square of each number in a list of integers.
           Sample Input: Arrays.asList(1, 2, 3, 4, 5)
           Expected Output: [1, 4, 9, 16, 25]
        */

        /*
           13. Find the length of the longest string in a list of strings.
           Sample Input: Arrays.asList("Apple", "Banana", "Avocado", "Cherry")
           Expected Output: 7 (from "Avocado")
        */

        /*
           14. Find the length of the shortest string in a list of strings.
           Sample Input: Arrays.asList("Apple", "Banana", "Kiwi", "Cherry")
           Expected Output: 4 (from "Kiwi")
        */

        /*
           15. Join a list of strings into a single string separated by a comma.
           Sample Input: Arrays.asList("Apple", "Banana", "Cherry")
           Expected Output: "Apple,Banana,Cherry"
        */

        /*
           16. Partition a list of integers into even and odd numbers.
           Sample Input: Arrays.asList(1, 2, 3, 4, 5, 6)
           Expected Output: {false=[1, 3, 5], true=[2, 4, 6]}
        */

        /*
           17. Group a list of integers by their remainder when divided by 3.
           Sample Input: Arrays.asList(1, 2, 3, 4, 5, 6, 7)
           Expected Output: {0=[3, 6], 1=[1, 4, 7], 2=[2, 5]}
        */

        /*
           18. Group a list of Person objects by age.
           Sample Input: [new Person("Alice", 25), new Person("Bob", 30), new Person("Charlie", 25)]
           Expected Output: {25=[Alice, Charlie], 30=[Bob]}
        */

        /*
           19. Find the average age of a list of Person objects.
           Sample Input: [new Person("Alice", 25), new Person("Bob", 35)]
           Expected Output: 30.0
        */

        /*
           20. Find the oldest person in a list of Person objects.
           Sample Input: [new Person("Alice", 25), new Person("Bob", 35)]
           Expected Output: Person("Bob", 35)
        */

        /*
           21. Find the youngest person in a list of Person objects.
           Sample Input: [new Person("Alice", 25), new Person("Bob", 35)]
           Expected Output: Person("Alice", 25)
        */

        /*
           22. Check if all numbers in a list of integers are positive.
           Sample Input: Arrays.asList(1, 2, 3, 4, 5)
           Expected Output: true
        */

        /*
           23. Check if any number in a list of integers is negative.
           Sample Input: Arrays.asList(1, 2, -3, 4, 5)
           Expected Output: true
        */

        /*
           24. Check if none of the numbers in a list of integers are zero.
           Sample Input: Arrays.asList(1, 2, 3, 4, 5)
           Expected Output: true
        */

        /*
           25. Find the first number in a list that is divisible by 5.
           Sample Input: Arrays.asList(1, 2, 3, 10, 5)
           Expected Output: 10
        */

        /*
           26. Find any number in a list that is divisible by 7.
           Sample Input: Arrays.asList(1, 2, 14, 3, 7)
           Expected Output: 14 (or 7)
        */

        /*
           27. Create a map from a list of strings where the key is the string and the value is its length.
           Sample Input: Arrays.asList("Apple", "Kiwi")
           Expected Output: {"Apple"=5, "Kiwi"=4}
        */

        /*
           28. Create a map from a list of strings where the key is the first character and the value is a list of strings starting with that character.
           Sample Input: Arrays.asList("Apple", "Banana", "Apricot", "Blueberry")
           Expected Output: {'A'=["Apple", "Apricot"], 'B'=["Banana", "Blueberry"]}
        */

        /*
           29. Find the sum of squares of all even numbers in a list of integers.
           Sample Input: Arrays.asList(1, 2, 3, 4)
           Expected Output: 20 (2^2 + 4^2 = 4 + 16 = 20)
        */

        /*
           30. Find the product of all numbers in a list of integers.
           Sample Input: Arrays.asList(1, 2, 3, 4)
           Expected Output: 24
        */

        /*
           31. Flatten a list of lists of integers into a single list of integers.
           Sample Input: Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4))
           Expected Output: [1, 2, 3, 4]
        */

        /*
           32. Flatten a list of lists of strings into a single list of strings and remove duplicates.
           Sample Input: Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"))
           Expected Output: ["a", "b", "c"]
        */

        /*
           33. Find the second highest number in a list of integers.
           Sample Input: Arrays.asList(1, 5, 3, 9, 2)
           Expected Output: 5
        */

        /*
           34. Find the second lowest number in a list of integers.
           Sample Input: Arrays.asList(1, 5, 3, 9, 2)
           Expected Output: 2
        */

        /*
           35. Find the string with the maximum number of vowels in a list of strings.
           Sample Input: Arrays.asList("Apple", "Banana", "Queue")
           Expected Output: "Queue"
        */

        /*
           36. Filter out strings that are palindromes from a list of strings.
           Sample Input: Arrays.asList("level", "hello", "radar", "world")
           Expected Output: ["hello", "world"]
        */

        /*
           37. Generate the first 10 Fibonacci numbers using streams.
           Sample Input: N/A
           Expected Output: [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
        */

        /*
           38. Generate the first 10 prime numbers using streams.
           Sample Input: N/A
           Expected Output: [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        */

        /*
           39. Find the most frequent character in all strings combined from a list of strings.
           Sample Input: Arrays.asList("apple", "banana", "cherry")
           Expected Output: 'a'
        */

        /*
           40. Remove duplicates from a list of integers and sort them in descending order.
           Sample Input: Arrays.asList(1, 2, 2, 3, 5, 5)
           Expected Output: [5, 3, 2, 1]
        */

        /*
           41. Find the strings that contain duplicate characters from a list of strings.
           Sample Input: Arrays.asList("apple", "banana", "cat", "dog")
           Expected Output: ["apple", "banana"]
        */

        /*
           42. Find the numbers that are perfect squares from a list of integers.
           Sample Input: Arrays.asList(1, 2, 3, 4, 5, 9)
           Expected Output: [1, 4, 9]
        */

        /*
           43. Find the numbers that are prime from a list of integers.
           Sample Input: Arrays.asList(2, 4, 6, 7, 9, 11)
           Expected Output: [2, 7, 11]
        */

        /*
           44. Check if two strings are anagrams of each other using streams.
           Sample Input: "listen", "silent"
           Expected Output: true
        */

        /*
           45. Find the median of a list of integers.
           Sample Input: Arrays.asList(1, 3, 2, 5, 4)
           Expected Output: 3.0
        */

        /*
           46. Find the mode of a list of integers.
           Sample Input: Arrays.asList(1, 2, 2, 3, 4)
           Expected Output: 2
        */

        /*
           47. Remove all non-alphanumeric characters from a list of strings and convert to lowercase.
           Sample Input: Arrays.asList("Hello!", "World@123")
           Expected Output: ["hello", "world123"]
        */

        /*
           48. Capitalize the first letter of each word in a list of strings.
           Sample Input: Arrays.asList("hello world", "java stream")
           Expected Output: ["Hello World", "Java Stream"]
        */

        /*
           49. Find the sum of the digits of each number in a list of integers.
           Sample Input: Arrays.asList(123, 456)
           Expected Output: [6, 15]
        */

        /*
           50. Find the longest common prefix among a list of strings.
           Sample Input: Arrays.asList("flower", "flow", "flight")
           Expected Output: "fl"
        */

        /*
           51. Create a stream of integers from an array.
           Sample Input: int[] arr = {1, 2, 3, 4, 5}
           Expected Output: Stream containing 1, 2, 3, 4, 5
        */

        /*
           52. Create a stream of 5 random numbers.
           Sample Input: N/A
           Expected Output: Stream of 5 random numbers (e.g., [0.12, 0.54, ...])
        */

        /*
           53. Create an infinite stream of even numbers and limit it to 10.
           Sample Input: N/A
           Expected Output: [0, 2, 4, 6, 8, 10, 12, 14, 16, 18]
        */

        /*
           54. Convert a Stream of integers to a List.
           Sample Input: Stream.of(1, 2, 3)
           Expected Output: [1, 2, 3] (List)
        */

        /*
           55. Convert a Stream of integers to a Set.
           Sample Input: Stream.of(1, 2, 2, 3)
           Expected Output: [1, 2, 3] (Set)
        */

        /*
           56. Convert a Stream of integers to an Array.
           Sample Input: Stream.of(1, 2, 3)
           Expected Output: Integer[] {1, 2, 3}
        */

        /*
           57. Convert a Stream of strings to a Map where key is string and value is length.
           Sample Input: Stream.of("a", "bb", "ccc")
           Expected Output: {"a"=1, "bb"=2, "ccc"=3}
        */

        /*
           58. Use peek to debug a stream pipeline, printing elements before and after filtering.
           Sample Input: Stream.of(1, 2, 3, 4)
           Expected Output: Print 1, 2, 3, 4 then Print 2, 4 (if filtering evens)
        */

        /*
           59. Skip the first 5 elements of a stream and print the rest.
           Sample Input: Stream.of(1, 2, 3, 4, 5, 6, 7)
           Expected Output: 6, 7
        */

        /*
           60. Limit a stream to the first 3 elements.
           Sample Input: Stream.of(1, 2, 3, 4, 5)
           Expected Output: 1, 2, 3
        */

        /*
           61. Find the first string in a list that has a length greater than 5.
           Sample Input: Arrays.asList("apple", "banana", "cherry")
           Expected Output: "banana"
        */

        /*
           62. Extract a list of names from a list of Person objects.
           Sample Input: [new Person("Alice", 25), new Person("Bob", 30)]
           Expected Output: ["Alice", "Bob"]
        */

        /*
           63. Extract a set of unique countries from a list of Person objects.
           Sample Input: [new Person("Alice", "USA"), new Person("Bob", "UK"), new Person("Charlie", "USA")]
           Expected Output: ["USA", "UK"]
        */

        /*
           64. Filter a map of names and ages to include only those where age is greater than 18.
           Sample Input: {"Alice"=17, "Bob"=20, "Charlie"=19}
           Expected Output: {"Bob"=20, "Charlie"=19}
        */

        /*
           65. Sort a map of names and ages by age.
           Sample Input: {"Alice"=25, "Bob"=20, "Charlie"=22}
           Expected Output: {"Bob"=20, "Charlie"=22, "Alice"=25} (LinkedHashMap or List of Entries)
        */

        /*
           66. Sort a map of names and ages by name.
           Sample Input: {"Charlie"=22, "Alice"=25, "Bob"=20}
           Expected Output: {"Alice"=25, "Bob"=20, "Charlie"=22}
        */

        /*
           67. Concatenate a list of strings using a custom delimiter, prefix, and suffix.
           Sample Input: Arrays.asList("A", "B", "C"), Delimiter: "-", Prefix: "[", Suffix: "]"
           Expected Output: "[A-B-C]"
        */

        /*
           68. Find the summary statistics (count, sum, min, average, max) of a list of integers.
           Sample Input: Arrays.asList(1, 2, 3, 4, 5)
           Expected Output: IntSummaryStatistics{count=5, sum=15, min=1, average=3.000000, max=5}
        */

        /*
           69. Partition a list of strings into those with length > 3 and those with length <= 3.
           Sample Input: Arrays.asList("hi", "hello", "world", "abc")
           Expected Output: {false=["hi", "abc"], true=["hello", "world"]}
        */

        /*
           70. Group a list of Person objects by city and then by age.
           Sample Input: [Person("Alice", "NY", 25), Person("Bob", "NY", 30), Person("Charlie", "LA", 25)]
           Expected Output: {"NY"={25=[Alice], 30=[Bob]}, "LA"={25=[Charlie]}}
        */

        /*
           71. Find the oldest person in each city from a list of Person objects.
           Sample Input: [Person("Alice", "NY", 25), Person("Bob", "NY", 30), Person("Charlie", "LA", 25)]
           Expected Output: {"NY"=Bob, "LA"=Charlie}
        */

        /*
           72. Calculate the total value of transactions for each day from a list of Transaction objects.
           Sample Input: [Transaction("2023-01-01", 100), Transaction("2023-01-01", 50), Transaction("2023-01-02", 200)]
           Expected Output: {"2023-01-01"=150, "2023-01-02"=200}
        */

        /*
           73. Find the longest sequence of consecutive numbers in a list of integers.
           Sample Input: Arrays.asList(1, 9, 3, 10, 4, 20, 2)
           Expected Output: 4 (for sequence 1, 2, 3, 4)
        */


        /*
           74. Group a list of strings by their length and count the number of strings in each group.
           Sample Input: Arrays.asList("a", "bb", "ccc", "d", "ee")
           Expected Output: {1=2, 2=2, 3=1}
        */

        /*
           75. Find the pair of numbers in a list with the minimum difference.
           Sample Input: Arrays.asList(1, 5, 3, 19, 18, 25)
           Expected Output: (18, 19)
        */

        /*
           76. Find the pair of numbers in a list with the maximum difference.
           Sample Input: Arrays.asList(1, 5, 3, 19, 18, 25)
           Expected Output: (1, 25)
        */

        /*
           77. Find the first non-repeating character in a string.
           Sample Input: "swiss"
           Expected Output: 'w'
        */

        /*
           78. Implement a custom collector to calculate the product of a list of integers.
           Sample Input: Arrays.asList(1, 2, 3, 4)
           Expected Output: 24
        */

        /*
           79. Implement a custom collector to join a list of strings in reverse order.
           Sample Input: Arrays.asList("a", "b", "c")
           Expected Output: "cba"
        */

        /*
           80. Check if a list of integers is sorted in ascending order.
           Sample Input: Arrays.asList(1, 2, 3, 4)
           Expected Output: true
        */

        /*
           81. Check if a list of strings is sorted in alphabetical order.
           Sample Input: Arrays.asList("a", "c", "b")
           Expected Output: false
        */

        /*
           82. Merge two sorted lists of integers into a single sorted list using streams.
           Sample Input: [1, 3, 5], [2, 4, 6]
           Expected Output: [1, 2, 3, 4, 5, 6]
        */

        /*
           83. Find the common elements between two lists of integers.
           Sample Input: [1, 2, 3], [2, 3, 4]
           Expected Output: [2, 3]
        */

        /*
           84. Find the difference between two lists of integers (elements in first but not second).
           Sample Input: [1, 2, 3], [2, 3, 4]
           Expected Output: [1]
        */

        /*
           85. Remove elements from a list of integers that are present in another list.
           Sample Input: List=[1, 2, 3, 4], Remove=[2, 4]
           Expected Output: [1, 3]
        */

        /*
           86. Remove strings from a list that match a specific regex (e.g., starts with a digit).
           Sample Input: ["1abc", "def", "2ghi"]
           Expected Output: ["def"]
        */

        /*
           87. Find the sum of the first N odd numbers.
           Sample Input: N=5
           Expected Output: 25 (1+3+5+7+9)
        */

        /*
           88. Find the average length of strings in a list.
           Sample Input: ["a", "bb", "ccc"]
           Expected Output: 2.0
        */

        /*
           89. Create a new list from a list of integers with each element multiplied by its index.
           Sample Input: [2, 4, 6]
           Expected Output: [0, 4, 12] (2*0, 4*1, 6*2)
        */

        /*
           90. Sort a list of strings based on the number of vowels they contain.
           Sample Input: ["banana", "apple", "sky"]
           Expected Output: ["sky", "apple", "banana"] (0, 2, 3 vowels)
        */

        /*
           91. Find the standard deviation of a list of integers.
           Sample Input: [10, 12, 23, 23, 16, 23, 21, 16]
           Expected Output: ~4.89
        */

        /*
           92. Find the string with the highest number of unique characters in a list.
           Sample Input: ["hello", "world", "abcde"]
           Expected Output: "abcde"
        */

        /*
           93. Partition a list of integers into prime and non-prime numbers.
           Sample Input: [2, 3, 4, 5, 6]
           Expected Output: {true=[2, 3, 5], false=[4, 6]}
        */

        /*
           94. Group a list of strings by their last character.
           Sample Input: ["apple", "banana", "kiwi", "grape"]
           Expected Output: {'e'=["apple", "grape"], 'a'=["banana"], 'i'=["kiwi"]}
        */

        /*
           95. Create a map from a list of Person objects where key is the ID and value is the Person object.
           Sample Input: [Person(1, "Alice"), Person(2, "Bob")]
           Expected Output: {1=Person(1, "Alice"), 2=Person(2, "Bob")}
        */

        /*
           96. Find the maximum sum of a contiguous subarray using streams (Kadane’s Algorithm).
           Sample Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
           Expected Output: 6 (subarray [4, -1, 2, 1])
        */

        /*
           97. Find the longest palindromic substring in a string.
           Sample Input: "babad"
           Expected Output: "bab" or "aba"
        */

        /*
           98. Implement a parallel stream to sum a large list of integers.
           Sample Input: Large list of integers
           Expected Output: Sum
        */

        /*
           99. Compare the performance (execution time) of sequential vs parallel stream for a complex operation.
           Sample Input: Large dataset
           Expected Output: Time in ms for both
        */

        /*
           100. Read a file line by line and count the number of words using streams.
           Sample Input: File with text "Hello world\nJava stream"
           Expected Output: 4
        */

        /*
           101. List all files in a directory and its subdirectories.
           Sample Input: Directory path
           Expected Output: List of file paths
        */

        /*
           102. Calculate the total revenue generated in a specific month from a list of orders.
           Sample Input: [Order("2023-01-15", 100), Order("2023-02-10", 200)], Month=1
           Expected Output: 100
        */

        /*
           103. Find the 3 highest paid employees from a list of employees.
           Sample Input: [Emp("A", 100), Emp("B", 200), Emp("C", 300), Emp("D", 400)]
           Expected Output: [Emp("D", 400), Emp("C", 300), Emp("B", 200)]
        */

        /*
           104. Count the occurrences of each character in a string.
           Sample Input: "hello"
           Expected Output: {'h'=1, 'e'=1, 'l'=2, 'o'=1}
        */

        /*
           105. Shuffle a list of integers using streams (custom collector or sort with random).
           Sample Input: [1, 2, 3, 4, 5]
           Expected Output: [3, 1, 5, 2, 4] (random order)
        */
    }
}
