package com.rest.crudOnEmployee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "projects")
public class Project {

    @Id
    private long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;



    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus = ProjectStatus.New;

    @JsonIgnore
    @ManyToMany(mappedBy = "projectList")
    private Set<Employee> employeeList = new HashSet<>();

    private String teamLeader;

}
