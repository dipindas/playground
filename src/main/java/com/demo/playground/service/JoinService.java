package com.demo.playground.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JoinService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> getInnerJoin() {
        // Inner Join: Returns records that have matching values in both tables.
        // Only employees assigned to a department are returned.
        String jpql = "SELECT e.name, d.name FROM Employee e INNER JOIN e.department d";
        return executeQuery(jpql);
    }

    public List<Map<String, Object>> getLeftJoin() {
        // Left Join (or Left Outer Join): Returns all records from the left table (Employee),
        // and the matched records from the right table (Department).
        // Employees without a department are also returned (department name will be null).
        String jpql = "SELECT e.name, d.name FROM Employee e LEFT JOIN e.department d";
        return executeQuery(jpql);
    }

    public List<Map<String, Object>> getRightJoin() {
        // Right Join (or Right Outer Join): Returns all records from the right table (Department),
        // and the matched records from the left table (Employee).
        // Departments with no employees are also returned (employee name will be null).
        String jpql = "SELECT e.name, d.name FROM Employee e RIGHT JOIN e.department d";
        return executeQuery(jpql);
    }

    public List<Map<String, Object>> getFullJoin() {
        // Full Join (or Full Outer Join): Returns all records when there is a match in either left or right table.
        // Returns all employees and all departments.
        // Using Native SQL as JPQL support for FULL JOIN is limited/vendor-specific.
        // We simulate FULL JOIN using UNION of LEFT JOIN and RIGHT JOIN to ensure compatibility across H2 versions and modes.
        String sql = "SELECT e.name as emp_name, d.name as dept_name " +
                     "FROM employees e LEFT JOIN departments d ON e.department_id = d.id " +
                     "UNION " +
                     "SELECT e.name as emp_name, d.name as dept_name " +
                     "FROM employees e RIGHT JOIN departments d ON e.department_id = d.id";

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> rows = query.getResultList();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object[] row : rows) {
            Map<String, Object> map = new HashMap<>();
            map.put("Employee Name", row[0]);
            map.put("Department Name", row[1]);
            result.add(map);
        }
        return result;
    }

    private List<Map<String, Object>> executeQuery(String jpql) {
        Query query = entityManager.createQuery(jpql);
        List<Object[]> rows = query.getResultList();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object[] row : rows) {
            Map<String, Object> map = new HashMap<>();
            map.put("Employee Name", row[0]);
            map.put("Department Name", row[1]);
            result.add(map);
        }
        return result;
    }
}
