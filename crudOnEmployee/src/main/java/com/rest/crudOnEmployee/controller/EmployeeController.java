package com.rest.crudOnEmployee.controller;


import com.rest.crudOnEmployee.model.Employee;
//import com.example.rest.crudOnEmployee.repository.EmployeeRepository;
import com.rest.crudOnEmployee.model.Project;
import com.rest.crudOnEmployee.service.EmployeeService;
import com.rest.crudOnEmployee.service.ProjectService;
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

    @PostMapping("/{empid}/project/{projectid}")
    public Employee AssignProject(@PathVariable("empid")long empid,@PathVariable("projectid") long projectid){
        return employeeService.setProject(empid,projectid);
    }
}
