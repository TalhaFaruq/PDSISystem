package com.tfworkers.PDSISystem.Model.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * The type Timeline.
 */
@Entity
@Data
public class Timeline {
	 @Id
	 @Column(nullable = false)
	 @GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long timeline_id;
	@NotBlank(message = "Name is Mandatory")
	private String name;
	@NotNull(message = "Name is Mandatory")
	private Date startDate;
	@NotNull(message = "Name is Mandatory")
	private Date endDate;
	private String updatedEndDate;
	private boolean isActive = true;
	private Date createdDate;
	private Date updatedDate;
	
	
	@OneToMany(targetEntity = Process.class,cascade = {
			CascadeType.ALL,
			CascadeType.MERGE
	},fetch = FetchType.EAGER)
	 private List<Process> processes = new ArrayList<Process>();



	/**
	 * Gets timeline id.
	 *
	 * @return the timeline id
	 */
	public Long getTimeline_id() {
		return timeline_id;
	}


	/**
	 * Sets timeline id.
	 *
	 * @param id the id
	 */
	public void setTimeline_id(Long id) {
		this.timeline_id = id;
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
	 * Gets start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets start date.
	 *
	 * @param startDate the start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets end date.
	 *
	 * @param endDate the end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * Sets updated date.
	 *
	 * @param updatedDate the updated date
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Gets updated end date.
	 *
	 * @return the updated end date
	 */
	public String getUpdatedEndDate() {
		return updatedEndDate;
	}


	/**
	 * Sets updated end date.
	 *
	 * @param updatedEndDate the updated end date
	 */
	public void setUpdatedEndDate(String updatedEndDate) {
		this.updatedEndDate = updatedEndDate;
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
	 * Gets processes.
	 *
	 * @return the processes
	 */
	public List<Process> getProcesses() {
		return processes;
	}


	/**
	 * Sets processes.
	 *
	 * @param processes the processes
	 */
	public void setProcesses(List<Process> processes) {
		this.processes = processes;
	}
	
	
	
}
