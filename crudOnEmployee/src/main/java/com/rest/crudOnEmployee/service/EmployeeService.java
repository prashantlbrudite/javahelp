package com.rest.crudOnEmployee.service;

import com.rest.crudOnEmployee.model.Employee;
import com.rest.crudOnEmployee.model.Project;
import com.rest.crudOnEmployee.repository.EmployeeRepository;
import com.rest.crudOnEmployee.repository.ProjectRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Set;


@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;
    ProjectRepository projectRepository;

    public EmployeeService(EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
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

    public Employee setProject(long empid, long projectid){
        Employee employee = employeeRepository.findById(empid).get();
        Set<Project> st = employee.getProjectList();
        Project project = projectRepository.findById(projectid).get();
        st.add(project);
        employeeRepository.save(employee);
        return employee;
    }

}
