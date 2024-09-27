package com.deloitte.demo.service;

import java.util.List;
import com.deloitte.demo.model.Employee;
import com.deloitte.demo.repository.EmployeeRepository;

public class EmployeeService {

    private EmployeeRepository employeeRepository = new EmployeeRepository();

    public Employee addEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.getEmployeeById(id);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteEmployee(id);
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {
        return employeeRepository.updateEmployee(id, updatedEmployee);
    }
    public List<Employee> getEmployeesByDeptId(int deptId) {
        return employeeRepository.getEmployeesByDeptId(deptId);
    }

}
