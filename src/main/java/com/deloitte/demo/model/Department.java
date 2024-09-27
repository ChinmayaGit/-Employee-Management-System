// Department.java
package com.deloitte.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dept") // Ensure this matches your SQL table name
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String empName; // Assuming this relates to an employee
    private String address;

    public Department() {
        super();
    }

    public Department(String empName, String address) {
        super();
        this.empName = empName;
        this.address = address;
    }

    public Department(int id, String empName, String address) {
        super();
        this.id = id;
        this.empName = empName;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Department [id=" + id + ", empName=" + empName + ", address=" + address + "]";
    }
}
