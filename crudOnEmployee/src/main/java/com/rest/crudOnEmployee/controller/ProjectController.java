package com.rest.crudOnEmployee.controller;

import com.rest.crudOnEmployee.model.Employee;
import com.rest.crudOnEmployee.model.Project;
import com.rest.crudOnEmployee.model.ProjectStatus;
import com.rest.crudOnEmployee.repository.ProjectRepository;
import com.rest.crudOnEmployee.service.ProjectService;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {


    ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping("/{id}/status")
    public ProjectStatus getStatus(@PathVariable("id") long id){
        return projectService.getProjectStatus(id);

    }


    @PutMapping("/{id}/setstatusnew")
    public Project setStatusToNew(@PathVariable("id") long id){
        return projectService.setProjectStatusToNew(id);
    }

    @PutMapping("/{id}/setstatusongoing")
    public Project setStatusToOnGoing(@PathVariable("id") long id){
        return projectService.setProjectStatusToOngoing(id);
    }

    @PutMapping("/{id}/setstatusended")
    public Project setStatusToEnded(@PathVariable("id") long id){
        return projectService.setProjectStatusToEnded(id);
    }


    @GetMapping("/")
    public List<Project> getAllProject(){
        return projectService.getProjects();
    }

    @GetMapping("/{id}")
    public Project getOneProject(@PathVariable("id") long id){
        return projectService.getOneProject(id);
    }

    @GetMapping("/newprojects")
    public List<Project> allNewProject(){
        return projectService.allNewProject();
    }

    @GetMapping("/ongoingprojects")
    public List<Project> allOnGoingProject(){
        return projectService.allOnGoingProject();
    }

    @GetMapping("/endedprojects")
    public List<Project> allEndedProjects(){
        return projectService.allEndedProject();
    }

    @PostMapping("/")
    public Project createProject(Project project){
        return projectService.createProject(project);
    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable("id") long id){
        return projectService.deleteProject(id);
    }





}
