package com.rest.crudOnEmployee.service;

import com.rest.crudOnEmployee.model.Project;
import com.rest.crudOnEmployee.model.ProjectStatus;
import com.rest.crudOnEmployee.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ProjectService {

    ProjectRepository projectRepository;

    public  ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public ProjectStatus  getProjectStatus(long id){
        Project existingProject =  projectRepository.findById(id).get();
        ProjectStatus existingStatus = existingProject.getProjectStatus();
        if(existingStatus == null){
            existingProject.setProjectStatus(ProjectStatus.New);
        }
        projectRepository.save((existingProject));
        return existingProject.getProjectStatus();
    }

    public Project setProjectStatusToNew(long id){
        Project existingProject =  projectRepository.findById(id).get();
        existingProject.setProjectStatus(ProjectStatus.New);
        projectRepository.save(existingProject);
        return existingProject;
    }

    public Project setProjectStatusToOngoing(long id){
        Project existingProject =  projectRepository.findById(id).get();
        existingProject.setProjectStatus(ProjectStatus.OnGoing);
        projectRepository.save(existingProject);
        return existingProject;
    }

    public Project setProjectStatusToEnded(long id){
        Project existingProject =  projectRepository.findById(id).get();
        existingProject.setProjectStatus(ProjectStatus.Ended);
        projectRepository.save(existingProject);
        return existingProject;
    }

    public List<Project> getProjects(){
        return projectRepository.findAll();
    }


    public Project getOneProject(long id){
        return projectRepository.findById(id).get();
    }


    public Project createProject(Project project){
        projectRepository.save(project);
        return project;
    }

    public String deleteProject(long id){
        Project project = projectRepository.findById(id).get();
        if(project.getEndDate().isBefore(LocalDate.now())) {
            projectRepository.deleteById(id);
            return "Successfully Deleted";
        }
        return "Cannot Delete Project Before End Date of Project";
    }

    public List<Project> allNewProject(){
        List<Project> allProjects = projectRepository.findAll();
        return allProjects.stream().filter(p -> p.getProjectStatus() == ProjectStatus.New).collect(Collectors.toList());

    }

    public List<Project> allOnGoingProject(){
        List<Project> allProjects = projectRepository.findAll();
        return allProjects.stream().filter(p -> p.getProjectStatus() == ProjectStatus.OnGoing).collect(Collectors.toList());
    }

    public List<Project> allEndedProject(){
        List<Project> allProjects = projectRepository.findAll();
        return allProjects.stream().filter(p -> p.getProjectStatus() == ProjectStatus.Ended).collect(Collectors.toList());
    }




}
