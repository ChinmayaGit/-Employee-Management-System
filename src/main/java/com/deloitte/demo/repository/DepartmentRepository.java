// DepartmentRepository.java
package com.deloitte.demo.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.deloitte.demo.model.Department;

public class DepartmentRepository {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePU");

    public Department addDepartment(Department department) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Department mergedDepartment = entityManager.merge(department);
        entityManager.getTransaction().commit();
        entityManager.close();
        return mergedDepartment;
    }

    public List<Department> getAllDepartments() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Department> departments = entityManager.createQuery("SELECT d FROM Department d", Department.class).getResultList();
        entityManager.close();
        return departments;
    }

    public Department getDepartmentById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Department department = entityManager.find(Department.class, id);
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

    public Department updateDepartment(int id, Department updatedDepartment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Department department = entityManager.find(Department.class, id);

        if (department != null) {
            department.setName(updatedDepartment.getName()); // Use getName()
            department.setLocation(updatedDepartment.getLocation()); // Use getLocation()
            entityManager.merge(department);
        } else {
            entityManager.getTransaction().rollback();
            entityManager.close();
            return null;
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        return department;
    }

}
