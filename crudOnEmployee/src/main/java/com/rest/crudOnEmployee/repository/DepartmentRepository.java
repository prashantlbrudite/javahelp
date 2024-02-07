package com.rest.crudOnEmployee.repository;

import com.rest.crudOnEmployee.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
