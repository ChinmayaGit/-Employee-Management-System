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
    private String firstName;
    private double salary;
    private String dept_id;  // Add dept_id field

    public Employee() {
        super();
    }

    public Employee(String firstName, double salary, String dept_id) {  // Updated constructor
        super();
        this.firstName = firstName;
        this.salary = salary;
        this.dept_id = dept_id;  // Set dept_id
    }

    public Employee(int id, String firstName, double salary, String dept_id) {  // Updated constructor
        super();
        this.id = id;
        this.firstName = firstName;
        this.salary = salary;
        this.dept_id = dept_id;  // Set dept_id
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDeptId() {  // Getter for dept_id
        return dept_id;
    }

    public void setDeptId(String dept_id) {  // Setter for dept_id
        this.dept_id = dept_id;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", salary=" + salary + ", dept_id=" + dept_id + "]";  // Include dept_id
    }
}
