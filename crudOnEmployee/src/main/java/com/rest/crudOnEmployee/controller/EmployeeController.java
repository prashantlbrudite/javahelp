package com.rest.crudOnEmployee.controller;


import com.rest.crudOnEmployee.model.Employee;
//import com.example.rest.crudOnEmployee.repository.EmployeeRepository;
import com.rest.crudOnEmployee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") int id){
        return employeeService.getEmployee(id);
    }

    @GetMapping("/")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @PostMapping("/")
    public String createEmployee(@RequestBody Employee employee){
        employeeService.createEmployee(employee);
        return "Employee created Successfully";
    }

    @PutMapping("/")
    public String updateEmployee(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
        return "Employee updated Successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable("id") int id){
        employeeService.deleteEmployee(id);
        return "Employee deleted Successfully";
    }
}
