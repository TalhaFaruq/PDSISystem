package com.tfworkers.PDSISystem.Model.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
@Entity
@Data
public class Project {
	 @Id
	 @Column(nullable = false)
	 @GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long project_id;
	private String name;
	private String Description;
	private String projectStatus;
	private Date createdDate;
	private Date updatedDate;
	private boolean isActive;
	
	@OneToMany(targetEntity = Timeline.class,cascade = CascadeType.ALL)
	 private List<Timeline> timelines = new ArrayList<Timeline>();
	
	@OneToMany(targetEntity = National.class,cascade = CascadeType.ALL)
	 private List<National> national = new ArrayList<National>();
	
	@OneToMany(targetEntity = International.class,cascade = CascadeType.ALL)
	 private List<International> international = new ArrayList<International>();
	
	@OneToMany(targetEntity = Budget.class,cascade = CascadeType.ALL)
	 private List<Budget> budgets = new ArrayList<Budget>();

	@ManyToMany(targetEntity = Tags.class,cascade = CascadeType.ALL)
	private List<Tags> tags = new ArrayList<Tags>();

	public Long getProject_id() {
		return project_id;
	}

	public void setProject_id(Long id) {
		this.project_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<Timeline> getTimelines() {
		return timelines;
	}

	public void setTimelines(List<Timeline> timelines) {
		this.timelines = timelines;
	}

	public List<National> getNational() {
		return national;
	}

	public void setNational(List<National> national) {
		this.national = national;
	}

	public List<International> getInternational() {
		return international;
	}

	public void setInternational(List<International> international) {
		this.international = international;
	}

	public List<Budget> getBudgets() {
		return budgets;
	}

	public void setBudgets(List<Budget> budgets) {
		this.budgets = budgets;
	}
	
	
	
}
