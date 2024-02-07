package com.rest.crudOnEmployee.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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

    @ManyToMany(mappedBy = "projectList")
    private List<Employee> employeeList;

    private String teamLeader;

}
