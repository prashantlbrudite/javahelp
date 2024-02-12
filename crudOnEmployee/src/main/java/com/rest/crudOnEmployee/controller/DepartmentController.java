package com.rest.crudOnEmployee.controller;


import com.rest.crudOnEmployee.model.Department;
import com.rest.crudOnEmployee.model.Employee;
import com.rest.crudOnEmployee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getAllDepartment(){
        return departmentService.getAllDepartment();
    }

    @GetMapping("/{id}/getEmployees")
    public ResponseEntity getEmployeesOfDepartment(@PathVariable("id") long id){
        return departmentService.getEmployeeByDepartmentId(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity getOneDepartment(@PathVariable("id") long id){
        return departmentService.getOneDepartment(id);
    }

    @PostMapping("/")
    public ResponseEntity postDepartment(@Validated @RequestBody  Department department){
        return  departmentService.createDepartment(department);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateDepartment(@PathVariable("id") Integer id,@Validated @RequestBody Department department){
        return departmentService.updateDepartmentById(id,department);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDepartment(@PathVariable("id") Integer id){
        return departmentService.deleteDepartment(id);
    }

}
