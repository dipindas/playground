package com.demo.playground.util.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapPlayground {

    public static void main(String args[]) {

        /**
         * Q1. Program to sort a map by its value
         *
         */
        Map<String, Integer> studentMap = new HashMap<String, Integer>();
        studentMap.put("Syam", 40);
        studentMap.put("Tim", 10);
        studentMap.put("Ben", 30);
        studentMap.put("Alice", 50);
        studentMap.put("Ken", 20);

        System.out.println("Trying out single line solution");
        studentMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
        studentMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);

        for (Map.Entry<String, Integer> entry: studentMap.entrySet()) {
            System.out.println("Name " + entry.getKey() + " Marks " + entry.getValue());
        }

        System.out.println("==== Printing List of map ====");
        List<Map.Entry<String, Integer>> mapList = new ArrayList<>(studentMap.entrySet());
        mapList.forEach(x -> System.out.println(x));

        System.out.println("==== Sorting the list of maps ====");
        Collections.sort(mapList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry1.getValue().compareTo(entry2.getValue());
            }
        });

        System.out.println("==== Printing List of map ====");
        mapList.forEach(x -> {
            System.out.println("key = " + x.getKey() + " value = " + x.getValue());
        });

        /**
         * Q2. Find max value from a list of Integers using stream
         *
         */

        Integer[] intArray = {20, 50, 10, 40, 30};
        List<Integer> numbers = Arrays.asList(intArray);
        Optional<Integer> result = numbers.stream().collect(Collectors.maxBy(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        }));
        System.out.println(result.get());

        /**
         * Q3. Find min value from a list of Integers using stream
         *
         */

        Integer[] intArray1 = {20, 50, 10, 40, 30};
        List<Integer> numbers1 = Arrays.asList(intArray1);
        Optional<Integer> result1 = numbers1.stream().collect(Collectors.minBy(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        }));
        System.out.println(result1.get());

        /**
         * Q3. Find avg of a list of Integers using stream
         *
         */

        Integer[] intArray2 = {20, 50, 10, 40, 30};
        List<Integer> numbers2 = Arrays.asList(intArray2);
        double average = numbers2.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println(average);

        /**
         * Q4. Find sum of a list of Integers using stream
         *
         */

        int sumValue = numbers2.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sumValue);

        /**
         * Q5. Find count of a list of Integers using stream
         *
         */
        long count = numbers2.stream().collect(Collectors.counting());
        System.out.println("Count = " + count);

        /**
         *
         * Points to note:
         * average(), min(), max() returns optional values OptionalDouble(average), OptionalInt(max, min)
         * count() and sum() gives result in long(count) and int(sum)
         *
         */

        /**
         * Q6. Find the max/min of the numbers
         */

        OptionalInt max = numbers2.stream().mapToInt(Integer::intValue).max();
        System.out.println(max.getAsInt());

        /**
         * 07. Join the list of items using comma
         */
        List<String> nameList = Arrays.asList("das", "tom", "tim");
        String str = nameList.stream().collect(Collectors.joining(","));
        System.out.println(str);

        /**
         * 08. Group by parameters to convert list to a map
         */
        Student7 st1 = new Student7(1, "das");
        Student7 st2 = new Student7(2, "tim");
        Student7 st3 = new Student7(3, "tom");
        Student7 st4 = new Student7(4, "mark");
        List<Student7> studList = new ArrayList<>();
        studList.add(st1);
        studList.add(st2);
        studList.add(st3);
        studList.add(st4);
        Map<Integer, List<Student7>> studMap = studList.stream().collect(Collectors.groupingBy(Student7::getRollNo));
        for(Map.Entry<Integer, List<Student7>> entry: studMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        /**
         * Populating EmployeeList
         */
        EmployeeNew emp1 = new EmployeeNew(4, "Rahul", 5000.0);
        EmployeeNew emp2 = new EmployeeNew(2, "Anjali", 7000.0);
        EmployeeNew emp3 = new EmployeeNew(1, "sachin", 4000.0);
        EmployeeNew emp4 = new EmployeeNew(3, "Subha", 6000.0);
        EmployeeNew emp5 = new EmployeeNew(5, "Fouzia", 5000.0);
        List<EmployeeNew> empList = new ArrayList<>();
        empList.add(emp1);
        empList.add(emp2);
        empList.add(emp3);
        empList.add(emp4);
        empList.add(emp5);
        /**
         * 09. Find second largest salary from EmployeeList
         */
        double secondHighestSal = empList.stream().map(EmployeeNew::getSalary)
                .distinct().sorted(Comparator.reverseOrder())
                .skip(1).findFirst().orElse(0d);
        System.out.println("Second highest salary " + secondHighestSal);

        double secondHighestSal2 = empList.stream()
                .sorted(Comparator.comparing(EmployeeNew::getSalary).reversed())
                .map(EmployeeNew::getSalary).skip(1).findFirst().orElse(0d);
        System.out.println("Second highest salary2 " + secondHighestSal2);

        /**
         * 10. Find the employee with highest salary
         */
        EmployeeNew emp = empList.stream().sorted(Comparator.comparing(EmployeeNew::getSalary).reversed())
                .findFirst().orElse(null);
        System.out.println("Employee with highest salary " + emp);

        EmployeeNew emp12 = empList.stream().max(Comparator.comparing(EmployeeNew::getSalary))
                .orElse(null);
        System.out.println("Employee with highest salary " + emp12);

        /**
         * 11. Find the employee details with second largest salary
         */
        EmployeeNew emp13 = empList.stream().sorted(Comparator.comparing(EmployeeNew::getSalary).reversed())
                .skip(1).findFirst().orElse(null);
        System.out.println("Employee with second highest salary " + emp13);

        double maxSal = empList.stream().map(EmployeeNew::getSalary)
                .max(Comparator.naturalOrder()).orElse(0d);
        System.out.println("Find the max salary of Employees " + maxSal);

        /**
         * 12. Group each employee name by their salary
         */

        Map<Double, List<String>> empMap1 = empList.stream()
                .collect(Collectors.groupingBy(EmployeeNew::getSalary,
                        Collectors.mapping(EmployeeNew::getName, Collectors.toList())));
        for(Map.Entry<Double, List<String>> entry: empMap1.entrySet()) {
            System.out.println("Key " + entry.getKey() + " value " + entry.getValue());
        }

        /**
         * 13. Find average salary of the employees
         */

        double avgSal = empList.stream().map(EmployeeNew::getSalary)
                .mapToDouble(Double::doubleValue).average().orElse(0d);
        System.out.println("Average salary " + avgSal);

        /**
         * 14. Find the number of employees having a salary greater than 5000
         */

        long count2 = empList.stream().map(EmployeeNew::getSalary)
                .filter(x -> x > 5000d).count();
        System.out.println("Employees having sal > 5000 = " + count2);

        /**
         * 15. Find the second and third lowest salary of the employees
         */

        List<Double> sal3 = empList.stream().map(EmployeeNew::getSalary).distinct()
                .sorted(Comparator.naturalOrder()).skip(1).limit(2).collect(Collectors.toList());
        System.out.println("Second and third lowest salaries " + sal3);

        /**
         * 16. Get all the employees name in comma separated format
         */

        String names1 = empList.stream().map(EmployeeNew::getName)
                .collect(Collectors.joining(", "));
        System.out.println("Names comma separated " + names1);

        /**
         * 17. Find the Employee names with second and third lowest salaries
         */

        List<String> empNames = empList.stream().sorted(Comparator.comparing(EmployeeNew::getSalary))
                .map(EmployeeNew::getName).skip(1).limit(2).collect(Collectors.toList());
        System.out.println("Employees with second and third lowest salaries " + empNames);

        /**
         * 18. Get the maximum salary among the list of Employees
         */
        double max2 = empList.stream().max(Comparator.comparing(EmployeeNew::getSalary))
                .map(EmployeeNew::getSalary).orElse(0d);
        System.out.println("Max salary of employees " + max2);

        /**
         * 19. Find the character count in a given word
         */

        List<Character> charList = Arrays.asList('m', 'a', 'l', 'a', 'y', 'a', 'l', 'a', 'm');
        Map<Character, Long> countMap = charList.stream()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println("Ans.19");
//		Map<Character, Long> countMap = charList.stream()
//				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for(Map.Entry<Character, Long> entry: countMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        /**
         * 20. Grouping by salary and sort the map based on salary
         */

        /**
         * 21. Sort employeeList by name irrespective of the case
         */

        /**
         * 22. Sort the employeelist first by salary then by names
         */

        List<EmployeeNew> sortedList = empList.stream()
                .sorted(Comparator.comparing(EmployeeNew::getSalary)
                        .thenComparing(EmployeeNew::getName)).collect(Collectors.toList());
        System.out.println("Sorted list of employees " + sortedList);

        /**
         * 23. Find Employees with same salary
         */
        Map<Double, Long> empMap3 = empList.stream().map(EmployeeNew::getSalary)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Double> salaryList = empMap3.entrySet().stream()
                .filter(x -> x.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println("salaries which are repeating " + salaryList);

//		empList.stream().filter(salary -> salary.getSalary())
//		.sorted((a,b)-> b-a).skip(1).findfirst();

        /**
         * 24. Replace character in a string with another
         */
        String name = "java";
        String result2 = name.chars()
                .mapToObj(c -> (char) c)
                .map(c -> c == 'a' ? 'b' : c)
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println("replaced String " + result2);

        /**
         * 25. Understanding the difference between mapToObj() and
         * mapToInt(Integer::intValue), mapToDouble(Double::doubleValue) etc.
         */

        /**
         * 26. Find the product of all the non-zero elements in an array.
         */
        int[] arr4 = {2, 3, 0, 5, 0 };
        int prod = Arrays.stream(arr4).filter(x -> x != 0).reduce(1, (a, b) -> a*b);
        System.out.println("Prod " + prod);

        /**
         * 27. Find the count of characters in given string.
         */
        String str2 = "microservice";
        long charCount = str2.chars().count();
        System.out.println("character count " + charCount);

        EmployeeDepartment em1 = new EmployeeDepartment("sam", "dep1", 1000.0);
        EmployeeDepartment em2 = new EmployeeDepartment("Tim", "dep2", 2000.0);
        EmployeeDepartment em3 = new EmployeeDepartment("Tom", "dep1", 3000.0);
        EmployeeDepartment em4 = new EmployeeDepartment("Ram", "dep2", 4000.0);
        EmployeeDepartment em5 = new EmployeeDepartment("Siva", "dep1", 1500.0);
        List<EmployeeDepartment> empList2 = new ArrayList<>();
        empList2.add(em1);
        empList2.add(em5);
        empList2.add(em4);
        empList2.add(em3);
        empList2.add(em2);
        /**
         * 28. Find department and highestsalary
         */
//		Map<String, >
    }

}

class EmployeeNew {
    private int rollNo;
    private String name;
    private double salary;

    public int getRollNo() {
        return rollNo;
    }
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "Employee [rollNo=" + rollNo + ", name=" + name + ", salary=" + salary + "]";
    }
    public EmployeeNew(int rollNo, String name, double salary) {
        super();
        this.rollNo = rollNo;
        this.name = name;
        this.salary = salary;
    }
}

class EmployeeDepartment{
    @Override
    public String toString() {
        return "EmployeeDepartment [name=" + name + ", department=" + department + ", salary=" + salary + "]";
    }
    public EmployeeDepartment(String name, String department, Double salary) {
        super();
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    private String name;
    private String department;
    private Double salary;
}

class Student7 {
    private int rollNo;
    private String name;

    public Student7(int rollNo, String name) {
        super();
        this.rollNo = rollNo;
        this.name = name;
    }
    public int getRollNo() {
        return rollNo;
    }
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}



