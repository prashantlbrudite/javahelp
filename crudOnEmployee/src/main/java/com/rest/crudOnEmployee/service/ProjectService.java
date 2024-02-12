package com.rest.crudOnEmployee.service;

import com.rest.crudOnEmployee.model.Project;
import com.rest.crudOnEmployee.model.ProjectStatus;
import com.rest.crudOnEmployee.repository.ProjectRepository;
import com.rest.crudOnEmployee.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProjectService {

    ProjectRepository projectRepository;

    public  ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public ResponseEntity getProjectStatus(long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project existingProject = projectOptional.get();
            ProjectStatus existingStatus = existingProject.getProjectStatus();
            if (existingStatus == null) {
                existingProject.setProjectStatus(ProjectStatus.New);
                projectRepository.save(existingProject);
                return ResponseHandler.responseBuilder("Project status set to New", HttpStatus.OK, existingProject.getProjectStatus(), "null");
            } else {
                return ResponseHandler.responseBuilder("Project status retrieved successfully", HttpStatus.OK, existingProject.getProjectStatus(), "null");
            }
        } else {
            return ResponseHandler.responseBuilder("Project not found", HttpStatus.NOT_FOUND, null, "Project not found with id: " + id);
        }
    }

    public ResponseEntity setProjectStatusToNew(long id) {
        return setProjectStatus(id, ProjectStatus.New);
    }

    public ResponseEntity setProjectStatusToOngoing(long id) {
        return setProjectStatus(id, ProjectStatus.OnGoing);
    }

    public ResponseEntity setProjectStatusToEnded(long id) {
        return setProjectStatus(id, ProjectStatus.Ended);
    }

    private ResponseEntity setProjectStatus(long id, ProjectStatus status) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project existingProject = projectOptional.get();
            existingProject.setProjectStatus(status);
            projectRepository.save(existingProject);
            return ResponseHandler.responseBuilder("Project status updated successfully", HttpStatus.OK, existingProject, "null");
        } else {
            return ResponseHandler.responseBuilder("Project not found", HttpStatus.NOT_FOUND, null, "Project not found with id: " + id);
        }
    }

    public ResponseEntity getProjects() {
        List<Project> allProjects = projectRepository.findAll();
        if (allProjects.isEmpty()) {
            return ResponseHandler.responseBuilder("No projects found", HttpStatus.NOT_FOUND, null, "No projects found");
        } else {
            return ResponseHandler.responseBuilder("List of all projects", HttpStatus.OK, allProjects, "null");
        }
    }

    public ResponseEntity getOneProject(long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        return projectOptional.map(project -> ResponseHandler.responseBuilder("Project found", HttpStatus.OK, project, "null")).orElseGet(() -> ResponseHandler.responseBuilder("Project not found", HttpStatus.NOT_FOUND, null, "Project not found with id: " + id));
    }

    public ResponseEntity createProject(Project project) {
        projectRepository.save(project);
        return ResponseHandler.responseBuilder("Project created successfully", HttpStatus.CREATED, null, "null");
    }

    public ResponseEntity deleteProject(long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            if (project.getEndDate().isBefore(LocalDate.now())) {
                projectRepository.deleteById(id);
                return ResponseHandler.responseBuilder("Project deleted successfully", HttpStatus.OK, "Successfully Deleted", "null");
            } else {
                return ResponseHandler.responseBuilder("Cannot delete project", HttpStatus.BAD_REQUEST, "Cannot Delete Project Before End Date of Project", "null");
            }
        } else {
            return ResponseHandler.responseBuilder("Project not found", HttpStatus.NOT_FOUND, null, "Project not found with id: " + id);
        }
    }

    public ResponseEntity allNewProject() {
        List<Project> allProjects = projectRepository.findAll();
        List<Project> newProjects = allProjects.stream().filter(p -> p.getProjectStatus() == ProjectStatus.New).collect(Collectors.toList());
        if (newProjects.isEmpty()) {
            return ResponseHandler.responseBuilder("No new projects found", HttpStatus.NOT_FOUND, null, "No new projects found");
        } else {
            return ResponseHandler.responseBuilder("List of all new projects", HttpStatus.OK, newProjects, "null");
        }
    }

    public ResponseEntity allOnGoingProject() {
        List<Project> allProjects = projectRepository.findAll();
        List<Project> ongoingProjects = allProjects.stream().filter(p -> p.getProjectStatus() == ProjectStatus.OnGoing).collect(Collectors.toList());
        if (ongoingProjects.isEmpty()) {
            return ResponseHandler.responseBuilder("No ongoing projects found", HttpStatus.NOT_FOUND, null, "No ongoing projects found");
        } else {
            return ResponseHandler.responseBuilder("List of all ongoing projects", HttpStatus.OK, ongoingProjects, "null");
        }
    }

    public ResponseEntity allEndedProject() {
        List<Project> allProjects = projectRepository.findAll();
        List<Project> endedProjects = allProjects.stream().filter(p -> p.getProjectStatus() == ProjectStatus.Ended).collect(Collectors.toList());
        if (endedProjects.isEmpty()) {
            return ResponseHandler.responseBuilder("No ended projects found", HttpStatus.NOT_FOUND, null, "No ended projects found");
        } else {
            return ResponseHandler.responseBuilder("List of all ended projects", HttpStatus.OK, endedProjects, "null");
        }
    }


}
