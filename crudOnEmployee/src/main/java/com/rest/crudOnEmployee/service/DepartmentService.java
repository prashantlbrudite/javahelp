package com.rest.crudOnEmployee.service;


import com.rest.crudOnEmployee.model.Department;
import com.rest.crudOnEmployee.model.Employee;
import com.rest.crudOnEmployee.repository.DepartmentRepository;
import com.rest.crudOnEmployee.repository.EmployeeRepository;
import com.rest.crudOnEmployee.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    DepartmentRepository departmentRepository;

    EmployeeRepository employeeRepository;


    public DepartmentService(DepartmentRepository departmentRepository,EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }


    // getAll
    public ResponseEntity getAllDepartment(){
        if(departmentRepository.findAll().isEmpty()){
            return ResponseHandler.responseBuilder("No Departments are found", HttpStatus.NOT_FOUND,null,"No Departments are found");
        }
        return ResponseHandler.responseBuilder("List of all departments", HttpStatus.OK,departmentRepository.findAll(),"null");
    }

    public ResponseEntity getOneDepartment(long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(Integer.valueOf(String.valueOf(id)));
        if (departmentOptional.isPresent()) {
            return ResponseHandler.responseBuilder("Department found", HttpStatus.OK, departmentOptional.get(), "null");
        } else {
            return ResponseHandler.responseBuilder("Department not found", HttpStatus.NOT_FOUND, null, "Department not found");
        }
    }

    public ResponseEntity getEmployeeByDepartmentId(long id) {
        List<Employee> allEmp = employeeRepository.findAll();
        List<Employee> filteredEmp = allEmp.stream().filter(e -> e.getDepartment().getId() == id).collect(Collectors.toList());
        if (filteredEmp.isEmpty()) {
            return ResponseHandler.responseBuilder("No employees found for department", HttpStatus.NOT_FOUND, null, "No employees found for department with id: " + id);
        } else {
            return ResponseHandler.responseBuilder("Employees found for department", HttpStatus.OK, filteredEmp, "null");
        }
    }

    public ResponseEntity createDepartment(Department department) {
        departmentRepository.save(department);
        return ResponseHandler.responseBuilder("Department created successfully", HttpStatus.CREATED, null, "null");
    }

    public ResponseEntity updateDepartmentById(Integer id, Department department) {
        Optional<Department> oldDeptOptional = departmentRepository.findById(id);
        if (oldDeptOptional.isPresent()) {
            Department oldDept = oldDeptOptional.get();
            oldDept.setName(department.getName());
            oldDept.setId(department.getId());
            departmentRepository.save(oldDept);
            return ResponseHandler.responseBuilder("Department updated successfully", HttpStatus.OK, null, "null");
        } else {
            return ResponseHandler.responseBuilder("Department not found", HttpStatus.NOT_FOUND, null, "Department not found with id: " + id);
        }
    }

    public ResponseEntity deleteDepartment(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isPresent()) {
            departmentRepository.deleteById(id);
            return ResponseHandler.responseBuilder("Department deleted successfully", HttpStatus.OK, null, "null");
        } else {
            return ResponseHandler.responseBuilder("Department not found", HttpStatus.NOT_FOUND, null, "Department not found with id: " + id);
        }
    }







}
