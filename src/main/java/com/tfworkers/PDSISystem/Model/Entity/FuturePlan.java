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
 * The type Future plan.
 */
@Entity
@Data
public class FuturePlan {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long future_id;
	@NotBlank(message = "Name is Mandatory")
	private String name;
	private String description;
	private String planType;
	private Date createdDate;
	private Date updatedDate;
	private boolean isActive = true;

	/**
	 * Gets future id.
	 *
	 * @return the future id
	 */
	public Long getFuture_id() {
		return future_id;
	}

	/**
	 * Sets future id.
	 *
	 * @param id the id
	 */
	public void setFuture_id(Long id) {
		this.future_id = id;
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
	 * Gets plan type.
	 *
	 * @return the plan type
	 */
	public String getPlanType() {
		return planType;
	}

	/**
	 * Sets plan type.
	 *
	 * @param planType the plan type
	 */
	public void setPlanType(String planType) {
		this.planType = planType;
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
}
