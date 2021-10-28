package com.tfworkers.PDSISystem.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Timeline {
	 @Id
	 @Column(nullable = false)
	 @GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private String name;
	private String startDate;
	private String endDate;
	private String updatedEndDate;
	private boolean isActive = false;
	private String createdDate;
	private String updatedDate;
	
	
	@OneToMany(targetEntity = Process.class,cascade = CascadeType.ALL)
	 @JoinColumn(name = "id")
	 private List<Process> processes = new ArrayList<Process>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
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


	public String getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


	public String getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}


	public List<Process> getProcesses() {
		return processes;
	}


	public void setProcesses(List<Process> processes) {
		this.processes = processes;
	}
	
	
	
}
