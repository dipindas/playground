package util;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class StreamPractice {

    public static void main(String args[]) {

        /* 1. Given a list of integers, using stream api, iterate through the integers and print them */

        /* 2. Given a list of integers, filter out the even numbers and print them */

        /* 3. Given a list of integers, find the sum of all numbers */

        /* 4. Given a list of integers, find the maximum element */

        /* 5. Given a list of strings, count the number of strings that start with a specific character */

        /* 6. Given a list of strings, convert all strings to uppercase */

        /* 7. Given a list of strings, sort them in alphabetical order */

        /* 8. Given a list of strings, sort them in reverse alphabetical order */

        /* 9. Given a list of integers, find the distinct numbers */

        /* 10. Given a list of integers, count the number of distinct numbers */

        /* 11. Given a list of integers, find the average of all numbers */

        /* 12. Given a list of integers, find the square of each number */

        /* 13. Given a list of strings, find the length of the longest string */

        /* 14. Given a list of strings, find the length of the shortest string */

        /* 15. Given a list of strings, join them into a single string separated by a comma */

        /* 16. Given a list of integers, partition them into even and odd numbers */

        /* 17. Given a list of integers, group them by their remainder when divided by 3 */

        /* 18. Given a list of Person objects, group them by age */

        /* 19. Given a list of Person objects, find the average age */

        /* 20. Given a list of Person objects, find the oldest person */

        /* 21. Given a list of Person objects, find the youngest person */

        /* 22. Given a list of integers, check if all numbers are positive */

        /* 23. Given a list of integers, check if any number is negative */

        /* 24. Given a list of integers, check if none of the numbers are zero */

        /* 25. Given a list of integers, find the first number that is divisible by 5 */

        /* 26. Given a list of integers, find any number that is divisible by 7 */

        /* 27. Given a list of strings, create a map where the key is the string and the value is its length */

        /* 28. Given a list of strings, create a map where the key is the first character and the value is a list of strings starting with that character */

        /* 29. Given a list of integers, find the sum of squares of all even numbers */

        /* 30. Given a list of integers, find the product of all numbers */

        /* 31. Given a list of lists of integers, flatten them into a single list of integers */

        /* 32. Given a list of lists of strings, flatten them into a single list of strings and remove duplicates */

        /* 33. Given a list of integers, find the second highest number */

        /* 34. Given a list of integers, find the second lowest number */

        /* 35. Given a list of strings, find the string with the maximum number of vowels */

        /* 36. Given a list of strings, filter out strings that are palindromes */

        /* 37. Given a list of integers, generate the first 10 Fibonacci numbers */

        /* 38. Given a list of integers, generate the first 10 prime numbers */

        /* 39. Given a list of strings, find the most frequent character in all strings combined */

        /* 40. Given a list of integers, remove the duplicates and sort them in descending order */

        /* 41. Given a list of strings, find the strings that contain duplicates characters */

        /* 42. Given a list of integers, find the numbers that are perfect squares */

        /* 43. Given a list of integers, find the numbers that are prime */

        /* 44. Given a list of strings, check if two strings are anagrams of each other using streams */

        /* 45. Given a list of integers, find the median */

        /* 46. Given a list of integers, find the mode */

        /* 47. Given a list of strings, remove all non-alphanumeric characters and convert to lowercase */

        /* 48. Given a list of strings, capitalize the first letter of each word */

        /* 49. Given a list of integers, find the sum of the digits of each number */

        /* 50. Given a list of strings, find the longest common prefix */

        /* 51. Create a stream of integers from an array */

        /* 52. Create a stream of random numbers */

        /* 53. Create an infinite stream of even numbers and limit it to 10 */

        /* 54. Convert a Stream to a List */

        /* 55. Convert a Stream to a Set */

        /* 56. Convert a Stream to an Array */

        /* 57. Convert a Stream to a Map */

        /* 58. Using peek to debug a stream pipeline */

        /* 59. Given a list of integers, skip the first 5 elements and print the rest */

        /* 60. Given a list of integers, limit the stream to the first 10 elements */

        /* 61. Given a list of strings, find the first string that has length greater than 5 */

        /* 62. Given a list of Person objects, get a list of their names */

        /* 63. Given a list of Person objects, get a set of their unique countries */

        /* 64. Given a map of names and ages, filter entries where age is greater than 18 */

        /* 65. Given a map of names and ages, sort it by age */

        /* 66. Given a map of names and ages, sort it by name */

        /* 67. Given a list of strings, concatenating them using a custom delimiter, prefix, and suffix */

        /* 68. Given a list of integers, find the summary statistics (count, sum, min, average, max) */

        /* 69. Given a list of strings, partition them into those with length > 5 and those with length <= 5 */

        /* 70. Given a list of Person objects, group them by city and then by age */

        /* 71. Given a list of Person objects, finding the oldest person in each city */

        /* 72. Given a list of Transaction objects, calculate the total value of transactions for each day */

        /* 73. Given a list of integers, find the longest sequence of consecutive numbers */

        /* 74. Given a list of strings, group them by their length and count the number of strings in each group */

        /* 75. Given a list of integers, find the pair of numbers with the minimum difference */

        /* 76. Given a list of integers, find the pair of numbers with the maximum difference */

        /* 77. Given a list of strings, find the first non-repeating character in the first string */

        /* 78. Given a list of integers, implement a custom collector to calculate the product of the numbers */

        /* 79. Given a list of strings, implement a custom collector to join them in reverse order */

        /* 80. Given a list of integers, check if the list is sorted */

        /* 81. Given a list of strings, check if the list is sorted */

        /* 82. Given a list of integers, merge two sorted lists into a single sorted list using streams */

        /* 83. Given a list of strings, find the common elements between two lists using streams */

        /* 84. Given a list of integers, find the difference between two lists using streams */

        /* 85. Given a list of integers, remove elements that are present in another list */

        /* 86. Given a list of strings, remove strings that match a specific regex */

        /* 87. Given a list of integers, find the sum of the first N odd numbers */

        /* 88. Given a list of strings, find the average length of the strings */

        /* 89. Given a list of integers, create a new list with each element multiplied by its index */

        /* 90. Given a list of strings, sort them based on the number of vowels they contain */

        /* 91. Given a list of integers, find the standard deviation */

        /* 92. Given a list of strings, find the string with the highest number of unique characters */

        /* 93. Given a list of integers, partition them into prime and non-prime numbers */

        /* 94. Given a list of strings, group them by the last character */

        /* 95. Given a list of Person objects, create a map where key is the ID and value is the Person object */

        /* 96. Given a list of integers, find the maximum sum of a contiguous subarray (Kadane’s Algorithm using streams - challenging) */

        /* 97. Given a list of strings, find the longest palindromic substring in the first string (using streams if possible) */

        /* 98. Implement a parallel stream to process a large list of integers and find the sum */

        /* 99. Compare the performance of sequential vs parallel stream for a large dataset */

        /* 100. Given a file path, read the file line by line using Files.lines() and count the number of words */

        /* 101. Given a directory path, list all files in the directory and subdirectories using Files.walk() */

        /* 102. Given a list of orders, calculate the total revenue generated in a specific month */

        /* 103. Given a list of employees, find the 3 highest paid employees */

        /* 104. Given a string, count the occurrences of each character using streams */

        /* 105. Implement a generic method to shuffle a list using streams */
    }
}
