package com.tfworkers.PDSISystem.Model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.util.Date;

/**
 * The type Process.
 */
@Entity
@Data
public class Process {
	 @Id
	 @Column(nullable = false)
	 @GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long process_id;
	@NotBlank(message = "Name is Mandatory")
	private String name;
	@NotBlank(message = "Description is Mandatory")
	private String description;
	private String approved;     //if approval needed otherwise it will empty
	private Date createdDate;
	private Date updatedDate;
	private boolean isActive = true;


	/**
	 * Gets process id.
	 *
	 * @return the process id
	 */
	public Long getProcess_id() {
		return process_id;
	}

	/**
	 * Sets process id.
	 *
	 * @param id the id
	 */
	public void setProcess_id(Long id) {
		this.process_id = id;
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
		return description;
	}

	/**
	 * Sets description.
	 *
	 * @param description the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets approved.
	 *
	 * @return the approved
	 */
	public String getApproved() {
		return approved;
	}

	/**
	 * Sets approved.
	 *
	 * @param approved the approved
	 */
	public void setApproved(String approved) {
		this.approved = approved;
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
	
	

}
