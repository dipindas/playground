package com.demo.playground.util.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {

        /**
         *  Calculate the sum of integers from 1 to 1,000,000 using a parallel stream.
         */

        long sum = IntStream.rangeClosed(1, 1000000).parallel().sum();
        System.out.println(sum);
        System.out.println("================================================");


        /**
         * Identify the first non-repeating character in a string.
         * String input = "swiss";
         */

        String input = "ss";//"swiss";
        Character c = input.chars().mapToObj(x -> (char)x).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(x -> x.getValue() == 1)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println(c);

        System.out.println("================================================");

        /**
         * Given a word, find the character frequency and sort it by the frequency (value) in descending order and store the
         * result in a map
         * String str = "microservice";
         */

        String str = "microservice";
        Map<Character, Long> charFreq = str.chars().mapToObj(x -> (char)x).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (old, newVal) -> newVal, LinkedHashMap::new));
        charFreq.entrySet().forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));

        System.out.println("================================================");

        /**
         * Count total distinct words (case-insensitive) across a list of sentences.
         * List<String> sentences = Arrays.asList("Java Stream API", "java stream problems");
         */

        List<String> sentences = Arrays.asList("Java Stream API", "java stream problems");
        long count = sentences.stream().flatMap(x -> Arrays.stream(x.split(" ")))
                .map(y -> y.toLowerCase())
                .distinct()
                .count();
        System.out.println("Distinct word count " + count);
        System.out.println("================================================");

        /**
         *  For each department, find the employee (get employee name) with the highest salary.
         *  Output: {BPO=bob, IT=ann}
         */
        Employee emp1 = new Employee(10, "sam", "IT", 9000);
        Employee emp2 = new Employee(11, "ram", "BPO", 6000);
        Employee emp3 = new Employee(12, "ann", "IT", 5000);
        Employee emp4 = new Employee(13, "bob", "BPO", 8000);
        List<Employee> empList = new ArrayList<>();
        empList.add(emp1);
        empList.add(emp2);
        empList.add(emp3);
        empList.add(emp4);

        Map<String, Employee> topEarners = empList.stream().collect(Collectors
                .toMap(Employee::getDepartment, e -> e, (e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2));
        topEarners.entrySet().forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));

        Map<String, String> topEarner = empList.stream().collect(Collectors.toMap(Employee::getDepartment,
                e -> e, (e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getName()));
        System.out.println(topEarner);
        System.out.println("================================================");

        /**
         * Find the average salary per department.
         */

        Map<String, Double> avgsalDept = empList.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)));
        avgsalDept.entrySet().stream().forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));
        System.out.println("================================================");

        Map<String, Double> sumSalDept = empList.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.summingDouble(Employee::getSalary)));
        sumSalDept.entrySet().stream().forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));
        System.out.println("================================================");

        /**
         * Get the department and its list of employee names.
         */

        Map<String, List<String>> empNameMap = empList.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.mapping(Employee::getName, Collectors.toList())));
        empNameMap.entrySet().forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));

        System.out.println("================================================");

        /**
         * Get the department and its list of unique employee names.
         */

        Map<String, Set<String>> empMapUnique = empList.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.mapping(Employee::getName, Collectors.toSet())));
        empMapUnique.entrySet().forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));

        System.out.println("================================================");

        /**
         * Get the department and its list of employee names in sorted order.
         */

        System.out.println("Names in sorted order");
        Map<String, List<String>> empMapWithSort = empList.stream().sorted(Comparator.comparing(Employee::getName))
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));
        empMapWithSort.entrySet().forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));

        System.out.println("================================================");


        /**
         * Get the department and its employee names comma separated.
         */
        Map<String, String> empMapCommaSeparated = empList.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.mapping(Employee::getName, Collectors.joining(", "))));
        empMapCommaSeparated.entrySet().forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));

        System.out.println("================================================");

        /**
         * Concatenate all employee names in a single String comma separated
         */

        String name12 = empList.stream().map(Employee::getName).collect(Collectors.joining(", "));
        System.out.println("Name printed comma separated " + name12);

        System.out.println("================================================");

        /**
         * Generate a summary of statistics (count, sum, min, average, max) for a list of employee salaries.
         */

//        empList.stream().sorted(Comparator.comparing(Employee::getSalary)).map(Employee::getSalary).collect(Collectors.toList());
//        Optional<Employee> emp121 = empList.stream().max(Comparator.comparing(Employee::getSalary));
//        if(emp121.isPresent()) System.out.println("Maximum salary " + emp121.get().getSalary());
        DoubleSummaryStatistics summary = empList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("Max " + summary.getMax());
        System.out.println("Min " + summary.getMin());
        System.out.println("sum " + summary.getSum());
        System.out.println("Count " + summary.getCount());
        System.out.println("================================================");

        /**
         * Get departments and its employees with employees list sorted in the ascending order of salaries
         */

        Map<String, List<Employee>> empMapWithSorted1 = empList.stream().sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.groupingBy(Employee::getDepartment, LinkedHashMap::new, Collectors.toList()));
        System.out.println("Map sorted by salary " + empMapWithSorted1);
        System.out.println("================================================");

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
        long count1 = numbers2.stream().collect(Collectors.counting());
        System.out.println("Count = " + count1);

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
        String str1 = nameList.stream().collect(Collectors.joining(","));
        System.out.println(str1);

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
        Employee emp11 = new Employee(4, "Rahul", 5000.0);
        Employee emp21 = new Employee(2, "Anjali", 7000.0);
        Employee emp31 = new Employee(1, "sachin", 4000.0);
        Employee emp41 = new Employee(3, "Subha", 6000.0);
        Employee emp51 = new Employee(5, "Fouzia", 5000.0);
        List<Employee> empList1 = new ArrayList<>();
        empList.add(emp11);
        empList.add(emp21);
        empList.add(emp31);
        empList.add(emp41);
        empList.add(emp51);
        /**
         * 09. Find second largest salary from EmployeeList
         */
        double secondHighestSal = empList1.stream().map(Employee::getSalary)
                .distinct().sorted(Comparator.reverseOrder())
                .skip(1).findFirst().orElse(0d);
        System.out.println("Second highest salary " + secondHighestSal);

        double secondHighestSal2 = empList1.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .map(Employee::getSalary).skip(1).findFirst().orElse(0d);
        System.out.println("Second highest salary2 " + secondHighestSal2);

        /**
         * 10. Find the employee with highest salary
         */
        Employee emp = empList.stream().sorted(Comparator.comparing(Employee::getSalary).reversed())
                .findFirst().orElse(null);
        System.out.println("Employee with highest salary " + emp);

        Employee emp12 = empList.stream().max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
        System.out.println("Employee with highest salary " + emp12);

        /**
         * 11. Find the employee details with second largest salary
         */
        Employee emp13 = empList.stream().sorted(Comparator.comparing(Employee::getSalary).reversed())
                .skip(1).findFirst().orElse(null);
        System.out.println("Employee with second highest salary " + emp13);

        double maxSal = empList.stream().map(Employee::getSalary)
                .max(Comparator.naturalOrder()).orElse(0d);
        System.out.println("Find the max salary of Employees " + maxSal);

        /**
         * 12. Group each employee name by their salary
         */

        Map<Double, List<String>> empMap1 = empList.stream()
                .collect(Collectors.groupingBy(Employee::getSalary,
                        Collectors.mapping(Employee::getName, Collectors.toList())));
        for(Map.Entry<Double, List<String>> entry: empMap1.entrySet()) {
            System.out.println("Key " + entry.getKey() + " value " + entry.getValue());
        }

        /**
         * 13. Find average salary of the employees
         */

        double avgSal = empList.stream().map(Employee::getSalary)
                .mapToDouble(Double::doubleValue).average().orElse(0d);
        System.out.println("Average salary " + avgSal);

        /**
         * 14. Find the number of employees having a salary greater than 5000
         */

        long count2 = empList.stream().map(Employee::getSalary)
                .filter(x -> x > 5000d).count();
        System.out.println("Employees having sal > 5000 = " + count2);

        /**
         * 15. Find the second and third lowest salary of the employees
         */

        List<Double> sal3 = empList.stream().map(Employee::getSalary).distinct()
                .sorted(Comparator.naturalOrder()).skip(1).limit(2).collect(Collectors.toList());
        System.out.println("Second and third lowest salaries " + sal3);

        /**
         * 16. Get all the employees name in comma separated format
         */

        String names1 = empList.stream().map(Employee::getName)
                .collect(Collectors.joining(", "));
        System.out.println("Names comma separated " + names1);

        /**
         * 17. Find the Employee names with second and third lowest salaries
         */

        List<String> empNames = empList.stream().sorted(Comparator.comparing(Employee::getSalary))
                .map(Employee::getName).skip(1).limit(2).collect(Collectors.toList());
        System.out.println("Employees with second and third lowest salaries " + empNames);

        /**
         * 18. Get the maximum salary among the list of Employees
         */
        double max2 = empList.stream().max(Comparator.comparing(Employee::getSalary))
                .map(Employee::getSalary).orElse(0d);
        System.out.println("Max salary of employees " + max2);

        /**
         * 19. Find the character count in a given word
         */

        List<Character> charList = Arrays.asList('m', 'a', 'l', 'a', 'y', 'a', 'l', 'a', 'm');
        Map<Character, Long> countMap = charList.stream()
                .collect(Collectors.groupingBy(f -> f, Collectors.counting()));
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

        List<Employee> sortedList = empList.stream()
                .sorted(Comparator.comparing(Employee::getSalary)
                        .thenComparing(Employee::getName)).collect(Collectors.toList());
        System.out.println("Sorted list of employees " + sortedList);

        /**
         * 23. Find Employees with same salary
         */
        Map<Double, Long> empMap3 = empList.stream().map(Employee::getSalary)
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
                .mapToObj(ch -> (char) ch)
                .map(ch -> ch == 'a' ? 'b' : ch)
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

class Employee {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String department;
    private String city;
    private double salary;
    private int yearsOfService;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Employee(int id, String name, int age, String gender, String department, String city, double salary, int yearsOfService) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.city = city;
        this.salary = salary;
        this.yearsOfService = yearsOfService;
    }

    // Standard Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getDepartment() { return department; }
    public String getCity() { return city; }
    public double getSalary() { return salary; }
    public int getYearsOfService() { return yearsOfService; }

    @Override
    public String toString() {
        return String.format("Employee[id=%d, name='%s', dept='%s', salary=%.2f]", id, name, department, salary);
    }

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
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
