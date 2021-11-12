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
 * The type International.
 */
@Entity
@Data
public class International {
	 @Id
	 @Column(nullable = false)
	 @GeneratedValue(strategy = GenerationType.IDENTITY )
	 private Long international_id;
	 @NotBlank(message = "Status Approval 'Write approved or not approved'")
	 private String foreignMinisterApproval;
	 private int internOfOrganizations;
	 private Date createdDate;
 	 private Date updatedDate;
	 private boolean isActive;

	/**
	 * Gets international id.
	 *
	 * @return the international id
	 */
	public Long getInternational_id() {
		return international_id;
	}

	/**
	 * Sets international id.
	 *
	 * @param id the id
	 */
	public void setInternational_id(Long id) {
		this.international_id = id;
	}

	/**
	 * Gets foreign minister approval.
	 *
	 * @return the foreign minister approval
	 */
	public String getForeignMinisterApproval() {
		return foreignMinisterApproval;
	}

	/**
	 * Sets foreign minister approval.
	 *
	 * @param foreignMinisterApproval the foreign minister approval
	 */
	public void setForeignMinisterApproval(String foreignMinisterApproval) {
		this.foreignMinisterApproval = foreignMinisterApproval;
	}

	/**
	 * Gets intern of organizations.
	 *
	 * @return the intern of organizations
	 */
	public int getInternOfOrganizations() {
		return internOfOrganizations;
	}

	/**
	 * Sets intern of organizations.
	 *
	 * @param internOfOrganizations the intern of organizations
	 */
	public void setInternOfOrganizations(int internOfOrganizations) {
		this.internOfOrganizations = internOfOrganizations;
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
