package com.rest.crudOnEmployee.repository;

import com.rest.crudOnEmployee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);
    Optional<Employee> findByIdAndDepartmentId(Long id, Long departmentId);
}
