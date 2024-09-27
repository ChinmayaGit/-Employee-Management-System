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
    private String name; // Change empName to name
    private String location; // Change address to location

    public Department() {
        super();
    }

    public Department(String name, String location) { // Updated constructor
        super();
        this.name = name; // Set name
        this.location = location; // Set location
    }

    public Department(int id, String name, String location) { // Updated constructor
        super();
        this.id = id;
        this.name = name; // Set name
        this.location = location; // Set location
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() { // Getter for name
        return name;
    }

    public void setName(String name) { // Setter for name
        this.name = name;
    }

    public String getLocation() { // Getter for location
        return location;
    }

    public void setLocation(String location) { // Setter for location
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department [id=" + id + ", name=" + name + ", location=" + location + "]"; // Updated toString
    }
}
