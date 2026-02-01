package com.demo.playground.util.leet;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {
//    public static int[] findNextGreater(int[] nums) {
//        int n = nums.length;
//        int[] result = new int[n];
//
//        // Initialize result array with -1
//        Arrays.fill(result, -1);
//
//        // Stack to store indices of elements
//        Stack<Integer> stack = new Stack<>();
//
//        for (int i = 0; i < n; i++) {
//            // While stack is not empty and current element is
//            // greater than the element at the index on top of stack
//            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
//                int indexToUpdate = stack.pop();
//                result[indexToUpdate] = nums[i];
//            }
//            // Push current index onto the stack
//            stack.push(i);
//        }
//
//        return result;
//    }

    public static void main(String[] args) {
        int[] input = {2, 4, 1, 3, 5};
        int[] output = findNextGreater(input);

        System.out.println("Input:  " + Arrays.toString(input));
        System.out.println("Output: " + Arrays.toString(output));
    }

    private static int[] findNextGreater(int input[]) {
        int n = input.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<n; i++) {
            while(!stack.isEmpty() && input[i] > input[stack.peek()]) {
                int indexToUpdate = stack.pop();
                result[indexToUpdate] = input[i];
            }
            stack.push(i);
        }
        return result;
    }
}