package com.tfworkers.PDSISystem.Model.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import org.hibernate.mapping.Set;

/**
 * The type Project.
 */
@Table(name = "project", indexes = {
        @Index(name = "created_date_index", columnList = "createdDate"),
        @Index(name = "active_index", columnList = "isActive")
})
@Data
@Entity
public class Project {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long project_id;
    @NotBlank(message = "Name is Mandatory")
    private String name;
    @NotBlank(message = "Description is Mandatory")
    private String Description;
    private boolean projectStatus;
    private Date createdDate;
    private Date updatedDate;
    private boolean isActive = true;

    //One project have many Timelines
    @OneToMany(targetEntity = Timeline.class, cascade = CascadeType.ALL)
    private List<Timeline> timelines = new ArrayList<>();

    //National Organizations which are working on current project
    @OneToMany(targetEntity = National.class, cascade = CascadeType.ALL)
    private List<National> national = new ArrayList<>();

    //International Organizations which are working on current project
    @OneToMany(targetEntity = International.class, cascade = CascadeType.ALL)
    private List<International> international = new ArrayList<>();

    //A project have many budget for travel, budget for construction material
    @OneToMany(targetEntity = Budget.class, cascade = CascadeType.ALL)
    private List<Budget> budgets = new ArrayList<>();


    @ManyToMany(targetEntity = Tags.class, cascade = CascadeType.ALL)
    private List<Tags> tags = new ArrayList<>();


    @OneToMany(targetEntity = CauseofAction.class, cascade = CascadeType.ALL)
    private List<CauseofAction> causeofActions = new ArrayList<>();
}
