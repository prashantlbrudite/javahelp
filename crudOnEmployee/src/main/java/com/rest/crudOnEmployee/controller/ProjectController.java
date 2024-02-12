package com.rest.crudOnEmployee.controller;

import com.rest.crudOnEmployee.model.Employee;
import com.rest.crudOnEmployee.model.Project;
import com.rest.crudOnEmployee.model.ProjectStatus;
import com.rest.crudOnEmployee.repository.ProjectRepository;
import com.rest.crudOnEmployee.service.ProjectService;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getStatus(@PathVariable("id") long id){
        return projectService.getProjectStatus(id);

    }


    @PutMapping("/{id}/setstatusnew")
    public ResponseEntity setStatusToNew(@PathVariable("id") long id){
        return projectService.setProjectStatusToNew(id);
    }

    @PutMapping("/{id}/setstatusongoing")
    public ResponseEntity setStatusToOnGoing(@PathVariable("id") long id){
        return projectService.setProjectStatusToOngoing(id);
    }

    @PutMapping("/{id}/setstatusended")
    public ResponseEntity setStatusToEnded(@PathVariable("id") long id){
        return projectService.setProjectStatusToEnded(id);
    }


    @GetMapping("/")
    public ResponseEntity getAllProject(){
        return projectService.getProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneProject(@PathVariable("id") long id){
        return projectService.getOneProject(id);
    }

    @GetMapping("/newprojects")
    public ResponseEntity allNewProject(){
        return projectService.allNewProject();
    }

    @GetMapping("/ongoingprojects")
    public ResponseEntity allOnGoingProject(){
        return projectService.allOnGoingProject();
    }

    @GetMapping("/endedprojects")
    public ResponseEntity allEndedProjects(){
        return projectService.allEndedProject();
    }

    @PostMapping("/")
    public ResponseEntity createProject(Project project){
        return projectService.createProject(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable("id") long id){
        return projectService.deleteProject(id);
    }

}
