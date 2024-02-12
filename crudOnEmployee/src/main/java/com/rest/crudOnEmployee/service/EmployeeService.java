package com.rest.crudOnEmployee.service;

import com.rest.crudOnEmployee.model.Employee;
import com.rest.crudOnEmployee.model.Project;
import com.rest.crudOnEmployee.repository.EmployeeRepository;
import com.rest.crudOnEmployee.repository.ProjectRepository;
import com.rest.crudOnEmployee.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;
    ProjectRepository projectRepository;

    public EmployeeService(EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    public ResponseEntity createEmployee(Employee employee) {
        employeeRepository.save(employee);
        return ResponseHandler.responseBuilder("Employee created successfully", HttpStatus.CREATED, null, "null");
    }

    public ResponseEntity updateEmployee(Employee employee) {
        employeeRepository.save(employee);
        return ResponseHandler.responseBuilder("Employee updated successfully", HttpStatus.OK, null, "null");
    }

    public ResponseEntity deleteEmployee(long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            employeeRepository.deleteById(id);
            return ResponseHandler.responseBuilder("Employee deleted successfully", HttpStatus.OK, null, "null");
        } else {
            return ResponseHandler.responseBuilder("Employee not found", HttpStatus.NOT_FOUND, null, "Employee not found with id: " + id);
        }
    }

    public ResponseEntity getEmployee(long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            return ResponseHandler.responseBuilder("Employee found", HttpStatus.OK, employeeOptional.get(), "null");
        } else {
            return ResponseHandler.responseBuilder("Employee not found", HttpStatus.NOT_FOUND, null, "Employee not found with id: " + id);
        }
    }

    public ResponseEntity getAllEmployee() {
        List<Employee> allEmployees = employeeRepository.findAll();
        if (allEmployees.isEmpty()) {
            return ResponseHandler.responseBuilder("No employees found", HttpStatus.NOT_FOUND, null, "No employees found");
        } else {
            return ResponseHandler.responseBuilder("List of all employees", HttpStatus.OK, allEmployees, "null");
        }
    }

    public ResponseEntity getEmployeeByName(String name) {
        List<Employee> allEmployees = employeeRepository.findAll();
        List<Employee> filteredEmployees = allEmployees.stream().filter(emp -> Objects.equals(emp.getName(), name)).collect(Collectors.toList());
        if (filteredEmployees.isEmpty()) {
            return ResponseHandler.responseBuilder("No employees found with name", HttpStatus.NOT_FOUND, null, "No employees found with name: " + name);
        } else {
            return ResponseHandler.responseBuilder("Employees found with name", HttpStatus.OK, filteredEmployees, "null");
        }
    }

    public ResponseEntity setProject(long empid, long projectid) {
        Optional<Employee> employeeOptional = employeeRepository.findById(empid);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            Set<Project> projects = employee.getProjectList();
            Optional<Project> projectOptional = projectRepository.findById(projectid);
            if (projectOptional.isPresent()) {
                Project project = projectOptional.get();
                projects.add(project);
                employeeRepository.save(employee);
                return ResponseHandler.responseBuilder("Project added to employee successfully", HttpStatus.OK, employee, "null");
            } else {
                return ResponseHandler.responseBuilder("Project not found", HttpStatus.NOT_FOUND, null, "Project not found with id: " + projectid);
            }
        } else {
            return ResponseHandler.responseBuilder("Employee not found", HttpStatus.NOT_FOUND, null, "Employee not found with id: " + empid);
        }
    }


}
