// DepartmentService.java
package com.deloitte.demo.service;

import java.util.List;
import com.deloitte.demo.model.Department;
import com.deloitte.demo.repository.DepartmentRepository;

public class DepartmentService {

    private DepartmentRepository departmentRepository = new DepartmentRepository();

    public Department addDepartment(Department department) {
        return departmentRepository.addDepartment(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }

    public Department getDepartmentById(int id) {
        return departmentRepository.getDepartmentById(id);
    }

    public void deleteDepartment(int id) {
        departmentRepository.deleteDepartment(id);
    }

    public Department updateDepartment(int id, Department updatedDepartment) {
        return departmentRepository.updateDepartment(id, updatedDepartment);
    }
}
