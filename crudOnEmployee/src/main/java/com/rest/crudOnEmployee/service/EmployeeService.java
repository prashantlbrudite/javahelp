package com.rest.crudOnEmployee.service;

import com.rest.crudOnEmployee.model.Employee;
import com.rest.crudOnEmployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;


@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public String createEmployee(Employee employee){
        employeeRepository.save(employee);
        return "Success";
    }

    public String updateEmployee(Employee employee){
        employeeRepository.save(employee);
        return "Success";
    }

    public String deleteEmployee(long id){
        employeeRepository.deleteById(id);
        return "Success";
    }

    public Employee getEmployee(long id){
        return employeeRepository.findById(id).get();
    }



    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeeByName(String name){
        List<Employee> allEmp = employeeRepository.findAll();
        return allEmp.stream().filter(emp -> Objects.equals(emp.getName(), name)).toList();
    }

}
