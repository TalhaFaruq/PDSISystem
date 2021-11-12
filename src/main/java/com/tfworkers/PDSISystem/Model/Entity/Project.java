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

    @OneToMany(targetEntity = Timeline.class, cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<Timeline> timelines = new ArrayList<Timeline>();

    @OneToMany(targetEntity = National.class, cascade = CascadeType.ALL)
    private List<National> national = new ArrayList<National>();

    @OneToMany(targetEntity = International.class, cascade = CascadeType.ALL)
    private List<International> international = new ArrayList<International>();

    @OneToMany(targetEntity = Budget.class, cascade = CascadeType.ALL)
    private List<Budget> budgets = new ArrayList<Budget>();

    @ManyToMany(targetEntity = Tags.class, cascade = CascadeType.ALL)
    private List<Tags> tags = new ArrayList<Tags>();

    @OneToMany(targetEntity = CauseofAction.class, cascade = CascadeType.ALL)
    private List<CauseofAction> causeofActions = new ArrayList<>();

    /**
     * Gets project id.
     *
     * @return the project id
     */
    public Long getProject_id() {
        return project_id;
    }

    /**
     * Sets project id.
     *
     * @param id the id
     */
    public void setProject_id(Long id) {
        this.project_id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * Is project status boolean.
     *
     * @return the boolean
     */
    public boolean isProjectStatus() {
        return projectStatus;
    }

    /**
     * Sets project status.
     *
     * @param projectStatus the project status
     */
    public void setProjectStatus(boolean projectStatus) {
        this.projectStatus = projectStatus;
    }

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public List<Tags> getTags() {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     */
    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    /**
     * Gets causeof actions.
     *
     * @return the causeof actions
     */
    public List<CauseofAction> getCauseofActions() {
        return causeofActions;
    }

    /**
     * Sets causeof actions.
     *
     * @param causeofActions the causeof actions
     */
    public void setCauseofActions(List<CauseofAction> causeofActions) {
        this.causeofActions = causeofActions;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets updated date.
     *
     * @return the updated date
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets updated date.
     *
     * @param updatedDate the updated date
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets active.
     *
     * @param isActive the is active
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Gets timelines.
     *
     * @return the timelines
     */
    public List<Timeline> getTimelines() {
        return timelines;
    }

    /**
     * Sets timelines.
     *
     * @param timelines the timelines
     */
    public void setTimelines(List<Timeline> timelines) {
        this.timelines = timelines;
    }

    /**
     * Gets national.
     *
     * @return the national
     */
    public List<National> getNational() {
        return national;
    }

    /**
     * Sets national.
     *
     * @param national the national
     */
    public void setNational(List<National> national) {
        this.national = national;
    }

    /**
     * Gets international.
     *
     * @return the international
     */
    public List<International> getInternational() {
        return international;
    }

    /**
     * Sets international.
     *
     * @param international the international
     */
    public void setInternational(List<International> international) {
        this.international = international;
    }

    /**
     * Gets budgets.
     *
     * @return the budgets
     */
    public List<Budget> getBudgets() {
        return budgets;
    }

    /**
     * Sets budgets.
     *
     * @param budgets the budgets
     */
    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }


}
