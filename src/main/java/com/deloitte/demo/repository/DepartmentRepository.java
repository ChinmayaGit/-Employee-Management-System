package com.deloitte.demo.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import com.deloitte.demo.model.Department;

public class DepartmentRepository {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePU");

    public List<Department> getAllDepartments() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Department> departments = null;
        try {
            TypedQuery<Department> query = entityManager.createQuery("SELECT d FROM Department d", Department.class);
            departments = query.getResultList();
        } finally {
            entityManager.close(); 
        }
        return departments;
    }
    
    public Department getDepartmentById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Department department = entityManager.find(Department.class, id);
        entityManager.close();
        return department; 
    }
    
    public Department updateDepartment(int id, Department updatedDepartment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        Department department = entityManager.find(Department.class, id);
        
        if (department != null) {
            department.setName(updatedDepartment.getName());
            department.setLocation(updatedDepartment.getLocation());
        } else {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return null;
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        return department;
    }
    
    public void deleteDepartment(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Department department = entityManager.find(Department.class, id);
        
        if (department != null) {
            entityManager.remove(department);
        }
        
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
