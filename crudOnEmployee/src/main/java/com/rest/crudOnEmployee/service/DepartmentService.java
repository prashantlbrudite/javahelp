package com.rest.crudOnEmployee.service;


import com.rest.crudOnEmployee.model.Department;
import com.rest.crudOnEmployee.model.Employee;
import com.rest.crudOnEmployee.repository.DepartmentRepository;
import com.rest.crudOnEmployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }

    public Department getOneDepartment(long id){
        return departmentRepository.findById(Integer.valueOf(String.valueOf(id))).get();
    }


    // getOne
    public List<Employee> getEmployeeByDepartmentId(long id){
        List<Employee> allEmp = employeeRepository.findAll();
        return allEmp.stream().filter(e -> e.getDepartment().getId() == id).collect(Collectors.toList());
    }

    public void createDepartment(Department department){
        departmentRepository.save(department);

    }

    public String updateDepartmentById(Integer id,Department department){
        Department oldDept = departmentRepository.findById(id).get();
        oldDept.setName(department.getName());
        oldDept.setId(department.getId());
        return "Updated Successfully";
    }

    public String deleteDepartment(Integer id){
        departmentRepository.deleteById(id);
        return "Successfully Deleted";
    }







}
