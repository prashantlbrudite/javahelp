package com.rest.crudOnEmployee.repository;

import com.rest.crudOnEmployee.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
