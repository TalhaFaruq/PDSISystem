package com.tfworkers.PDSISystem.Model.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Data
public class Timeline {
	 @Id
	 @Column(nullable = false)
	 @GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long timeline_id;
	private String name;
	private Date startDate;
	private Date endDate;
	private String updatedEndDate;
	private boolean isActive = false;
	private Date createdDate;
	private Date updatedDate;
	
	
	@OneToMany(targetEntity = Process.class,cascade = CascadeType.ALL)
	 private List<Process> processes = new ArrayList<Process>();

	@JsonIgnore
	@ManyToOne(targetEntity = Project.class,cascade = CascadeType.ALL)
	 private Project project = new Project();



	public Long getTimeline_id() {
		return timeline_id;
	}


	public void setTimeline_id(Long id) {
		this.timeline_id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedEndDate() {
		return updatedEndDate;
	}


	public void setUpdatedEndDate(String updatedEndDate) {
		this.updatedEndDate = updatedEndDate;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public List<Process> getProcesses() {
		return processes;
	}


	public void setProcesses(List<Process> processes) {
		this.processes = processes;
	}
	
	
	
}
