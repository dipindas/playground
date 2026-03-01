package com.demo.playground.service.jpa;

import com.demo.playground.entity.jpa.Address;
import com.demo.playground.entity.jpa.Animal;
import com.demo.playground.entity.jpa.BankTransferPayment;
import com.demo.playground.entity.jpa.Bike;
import com.demo.playground.entity.jpa.Car;
import com.demo.playground.entity.jpa.Cat;
import com.demo.playground.entity.jpa.CreditCardPayment;
import com.demo.playground.entity.jpa.Department;
import com.demo.playground.entity.jpa.Desk;
import com.demo.playground.entity.jpa.Dog;
import com.demo.playground.entity.jpa.Employee;
import com.demo.playground.entity.jpa.Payment;
import com.demo.playground.entity.jpa.Project;
import com.demo.playground.entity.jpa.Vehicle;
import com.demo.playground.repository.jpa.AnimalRepository;
import com.demo.playground.repository.jpa.DepartmentRepository;
import com.demo.playground.repository.jpa.EmployeeRepository;
import com.demo.playground.repository.jpa.PaymentRepository;
import com.demo.playground.repository.jpa.VehicleRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JpaDemoService {

    private final EntityManager entityManager;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final VehicleRepository vehicleRepository;
    private final AnimalRepository animalRepository;
    private final PaymentRepository paymentRepository;

    /**
     * Demonstrates Entity Lifecycle States (Transient -> Managed -> Detached -> Removed).
     */
    @Transactional
    public String demonstrateLifecycle() {
        StringBuilder sb = new StringBuilder();

        // 1. Transient State (New object, not associated with a persistence context)
        Employee emp = new Employee("John Doe");
        sb.append("Created new Employee: ").append(emp.getName()).append(" (State: Transient)\n");

        // 2. Managed State (Saved via repository/entityManager)
        employeeRepository.save(emp);
        sb.append("Saved Employee with ID: ").append(emp.getId()).append(" (State: Managed)\n");

        // 3. Detached State
        // Spring Data JPA doesn't expose a detach() method directly because it abstracts away
        // the EntityManager. However, we can use entityManager.clear() to clear the entire context,
        // or just keep using entityManager.detach(emp) for this specific entity.
        entityManager.detach(emp);
        sb.append("Detached Employee from EntityManager (State: Detached)\n");

        // At this point, changes won't be synced to the DB automatically.
        emp.setName("John Detached");
        sb.append("Changed name of Detached Employee to: ").append(emp.getName()).append(". This will NOT be saved to DB.\n");

        // Merge back to Managed state (re-attach).
        // In Spring Data JPA, save() on an existing entity acts as merge() under the hood.
        Employee managedEmp = employeeRepository.save(emp);
        sb.append("Merged Employee back via employeeRepository.save(). (State: Managed). Name is now: ").append(managedEmp.getName()).append("\n");

        // 4. Removed State
        // In Spring Data JPA, delete() acts as remove() under the hood.
        employeeRepository.delete(managedEmp);
        sb.append("Removed Employee from DB via employeeRepository.delete() (State: Removed)\n");

        return sb.toString();
    }

    /**
     * Demonstrates the Persistence Context / First-Level Cache.
     */
    @Transactional
    public String demonstrateFirstLevelCache() {
        StringBuilder sb = new StringBuilder();

        Employee emp = new Employee("Cache Demo User");
        employeeRepository.save(emp); // Flushes/saves to DB, entity is now managed and in cache.
        sb.append("Saved new Employee with ID: ").append(emp.getId()).append("\n");

        // employeeRepository.findById() delegates to entityManager.find() under the hood
        // for primary key lookups, so it leverages the First-Level Cache exactly the same way.

        // The first find may hit the DB if it wasn't just saved, but since it was just saved,
        // it's already in the persistence context (L1 cache).
        long startTime = System.nanoTime();
        Employee cachedEmp1 = employeeRepository.findById(emp.getId()).orElse(null);
        long duration1 = System.nanoTime() - startTime;
        sb.append("1st Find Duration (ns): ").append(duration1).append(" (Hit L1 Cache)\n");

        // The second find for the same ID within the SAME transaction will definitely hit the L1 cache.
        // It will NOT trigger a SQL query.
        startTime = System.nanoTime();
        Employee cachedEmp2 = employeeRepository.findById(emp.getId()).orElse(null);
        long duration2 = System.nanoTime() - startTime;
        sb.append("2nd Find Duration (ns): ").append(duration2).append(" (Hit L1 Cache - Notice it's faster and no SQL logs appear)\n");

        // Prove they are the exact same object reference
        sb.append("Are cachedEmp1 and cachedEmp2 the exact same object instance? ").append(cachedEmp1 == cachedEmp2).append("\n");

        return sb.toString();
    }

    /**
     * Demonstrates Mappings: OneToOne, OneToMany, ManyToOne, ManyToMany.
     */
    @Transactional
    public String demonstrateMappings() {
        StringBuilder sb = new StringBuilder();

        // --- Uni-directional OneToOne ---
        // Employee has an Address. Address does not know about Employee.
        // Internally: JPA saves Address first, then saves Employee with Address ID in `address_id` column.
        Employee emp = new Employee("Mapping Demo User");
        Address address = new Address("123 Main St", "Tech City");
        emp.setAddress(address); // Cascade.ALL handles saving Address.
        employeeRepository.save(emp);
        sb.append("Created Employee with Uni-directional OneToOne Address.\n");

        // --- Uni-directional OneToMany ---
        // Department has a List of Desks. Desk does not know about Department.
        // Internally: Because we specified @JoinColumn(name="department_id") on the Department's List<Desk>,
        // JPA will insert the Desks, then execute an UPDATE statement to set `department_id` on the Desks.
        Department dept = new Department("Engineering");
        dept.addDesk(new Desk("Desk-A1"));
        dept.addDesk(new Desk("Desk-A2"));
        departmentRepository.save(dept); // Cascade.ALL handles saving Desks.
        sb.append("Created Department with Uni-directional OneToMany Desks.\n");

        // --- Uni-directional ManyToOne ---
        // A new Employee is assigned to a Department. The Department does not keep a List of Employees.
        // Internally: JPA saves the Employee and sets the `department_id` column in the Employee table.
        Employee emp2 = new Employee("Second User");
        emp2.setDepartment(dept);
        employeeRepository.save(emp2);
        sb.append("Created second Employee with Uni-directional ManyToOne reference to Department.\n");

        // --- Bi-directional ManyToMany ---
        // Employee and Project know about each other. Employee is the owner (defines @JoinTable).
        // Internally: JPA creates a junction table (`jpa_employee_project`) to store the relationships.
        // Since Employee is the owner, we must persist the Employee to save the relationship.
        Project p1 = new Project("Project Alpha");
        Project p2 = new Project("Project Beta");
        emp.addProject(p1);
        emp.addProject(p2);
        employeeRepository.save(emp);
        sb.append("Assigned first Employee to two Projects via Bi-directional ManyToMany.\n");

        return sb.toString();
    }

    /**
     * Demonstrates Inheritance (SINGLE_TABLE, JOINED, and TABLE_PER_CLASS strategies).
     * This method shows how JPA maps Object-Oriented inheritance onto Relational tables.
     */
    @Transactional
    public String demonstrateInheritance() {
        StringBuilder sb = new StringBuilder();

        // 1. SINGLE_TABLE Strategy (Default)
        // What it does: Maps all classes in the hierarchy (Vehicle, Car, Bike) to a single database table (jpa_vehicle).
        // Pros: Best performance because there are no JOINs required to query subclasses.
        // Cons: Subclass-specific columns (e.g., number_of_doors, has_sidecar) must be nullable, wasting space.
        // Mechanism: Uses a @DiscriminatorColumn (e.g., 'vehicle_type' = 'CAR' or 'BIKE') to tell JPA which subclass a row belongs to.
        Car car = new Car("Toyota", 4);
        Bike bike = new Bike("Harley Davidson", false);
        vehicleRepository.save(car);
        vehicleRepository.save(bike);

        sb.append("--- SINGLE_TABLE Strategy ---\n");
        sb.append("Saved Car (Manufacturer: ").append(car.getManufacturer()).append(", Doors: ").append(car.getNumberOfDoors()).append(")\n");
        sb.append("Saved Bike (Manufacturer: ").append(bike.getManufacturer()).append(", Has Sidecar: ").append(bike.isHasSidecar()).append(")\n");

        List<Vehicle> vehicles = vehicleRepository.findAll();
        sb.append("Retrieved Vehicles (Notice: queries just one table 'jpa_vehicle'):\n");
        for (Vehicle v : vehicles) {
            sb.append("- ").append(v.getClass().getSimpleName()).append(": ").append(v.getManufacturer()).append("\n");
        }

        // 2. JOINED Strategy
        // What it does: Creates a separate table for each class in the hierarchy (jpa_animal, jpa_dog, jpa_cat).
        // Pros: Data is normalized. Subclass tables only contain their specific columns, so no nulls.
        // Cons: Slower performance because fetching a subclass requires a SQL JOIN between the parent table and subclass table.
        // Mechanism: The subclass tables (jpa_dog) have a foreign key pointing back to the primary key of the parent table (jpa_animal).
        Dog dog = new Dog("Canine", "Golden Retriever");
        Cat cat = new Cat("Feline", true);
        animalRepository.save(dog);
        animalRepository.save(cat);

        sb.append("\n--- JOINED Strategy ---\n");
        sb.append("Saved Dog (Species: ").append(dog.getSpecies()).append(", Breed: ").append(dog.getBreed()).append(")\n");
        sb.append("Saved Cat (Species: ").append(cat.getSpecies()).append(", Indoor: ").append(cat.isIndoor()).append(")\n");

        List<Animal> animals = animalRepository.findAll();
        sb.append("Retrieved Animals (Notice: executes SQL JOINs across jpa_animal, jpa_dog, and jpa_cat):\n");
        for (Animal a : animals) {
            sb.append("- ").append(a.getClass().getSimpleName()).append(": ").append(a.getSpecies()).append("\n");
        }

        // 3. TABLE_PER_CLASS Strategy
        // What it does: Creates a separate table ONLY for the concrete subclasses (jpa_credit_card_payment, jpa_bank_transfer_payment). The parent class (Payment) does not get a table.
        // Pros: No JOINs are needed when querying a specific subclass table.
        // Cons: Polymorphic queries (e.g., querying the base PaymentRepository) are very slow because they require complex SQL UNIONs across all subclass tables.
        // Mechanism: Parent class fields (amount, id) are duplicated into every subclass table.
        CreditCardPayment ccPayment = new CreditCardPayment(100.50, "1234-5678-9012-3456");
        BankTransferPayment btPayment = new BankTransferPayment(500.00, "IBAN987654321");
        paymentRepository.save(ccPayment);
        paymentRepository.save(btPayment);

        sb.append("\n--- TABLE_PER_CLASS Strategy ---\n");
        sb.append("Saved CC Payment (Amount: ").append(ccPayment.getAmount()).append(", Card: ").append(ccPayment.getCardNumber()).append(")\n");
        sb.append("Saved Bank Transfer Payment (Amount: ").append(btPayment.getAmount()).append(", Account: ").append(btPayment.getBankAccountId()).append(")\n");

        List<Payment> payments = paymentRepository.findAll();
        sb.append("Retrieved Payments (Notice: executes a SQL UNION to combine results from all tables):\n");
        for (Payment p : payments) {
            sb.append("- ").append(p.getClass().getSimpleName()).append(": ").append(p.getAmount()).append("\n");
        }

        return sb.toString();
    }
}
