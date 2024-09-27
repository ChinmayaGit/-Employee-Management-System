// Employee.java
package com.deloitte.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name; // Change firstName to name
    private double salary;
    private int deptId;  // Use int for deptId to match database type

    public Employee() {
        super();
    }

    public Employee(String name, double salary, int deptId) {  // Updated constructor
        super();
        this.name = name;  // Set name
        this.salary = salary;
        this.deptId = deptId;  // Set deptId
    }

    public Employee(int id, String name, double salary, int deptId) {  // Updated constructor
        super();
        this.id = id;
        this.name = name;  // Set name
        this.salary = salary;
        this.deptId = deptId;  // Set deptId
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {  // Updated getter
        return name;
    }

    public void setName(String name) {  // Updated setter
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDeptId() {  // Updated getter to int
        return deptId;
    }

    public void setDeptId(int deptId) {  // Updated setter to int
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", deptId=" + deptId + "]";  // Updated toString
    }
}
