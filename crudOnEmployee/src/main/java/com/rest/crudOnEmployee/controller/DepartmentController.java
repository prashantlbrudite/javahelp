package com.rest.crudOnEmployee.controller;


import com.rest.crudOnEmployee.model.Department;
import com.rest.crudOnEmployee.model.Employee;
import com.rest.crudOnEmployee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }


    @GetMapping("/")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartment();
    }

    @GetMapping("/{id}/getEmployees")
    public List<Employee> getEmployeesOfDepartment(@PathVariable("id") long id){
        return departmentService.getEmployeeByDepartmentId(id);
    }


    @GetMapping("/{id}")
    public Department getOneDepartment(@PathVariable("id") long id){
        return departmentService.getOneDepartment(id);
    }

    @PostMapping("/")
    public Department postDepartment(@Validated @RequestBody  Department department){
        departmentService.createDepartment(department);
        return department;
    }

    @PutMapping("/{id}")
    public String updateDepartment(@PathVariable("id") Integer id,@Validated @RequestBody Department department){
        return departmentService.updateDepartmentById(id,department);

    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable("id") Integer id){
        return departmentService.deleteDepartment(id);
    }

}
