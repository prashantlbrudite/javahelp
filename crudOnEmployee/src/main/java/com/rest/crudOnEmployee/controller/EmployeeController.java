package com.rest.crudOnEmployee.controller;


import com.rest.crudOnEmployee.model.Employee;
//import com.example.rest.crudOnEmployee.repository.EmployeeRepository;
import com.rest.crudOnEmployee.model.Project;
import com.rest.crudOnEmployee.service.EmployeeService;
import com.rest.crudOnEmployee.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService, ProjectService projectService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{id}")
    public ResponseEntity getEmployee(@PathVariable("id") int id){
        return employeeService.getEmployee(id);
    }

    @GetMapping("/")
    public ResponseEntity getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @PostMapping("/")
    public ResponseEntity createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);

    }

    @PutMapping("/")
    public ResponseEntity updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") int id){
        return employeeService.deleteEmployee(id);
//        return "Employee deleted Successfully";
    }

    @PostMapping("/{empid}/project/{projectid}")
    public ResponseEntity AssignProject(@PathVariable("empid")long empid,@PathVariable("projectid") long projectid){
        return employeeService.setProject(empid,projectid);
    }
}
