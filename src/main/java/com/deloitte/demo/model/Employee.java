package com.deloitte.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.deloitte.demo.repository.DepartmentRepository;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private double salary;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    public Employee() {
        super();
    }


    public Employee(int id, String firstName, double salary, Department department) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.salary = salary;
        this.department = department; 
    }

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

    public int getDepartmentId() {
        return department != null ? department.getId() : 0; // Return department ID if exists, otherwise return 0
    }

    public void setDepartmentId(int departmentId, DepartmentRepository departmentRepository) {
        Department department = departmentRepository.getDepartmentById(departmentId);
        if (department != null) {
            this.department = department;
        } else {
            throw new IllegalArgumentException("Department not found with ID: " + departmentId);
        }
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", salary=" + salary + ", departmentId=" + getDepartmentId() + "]";
    }
}
