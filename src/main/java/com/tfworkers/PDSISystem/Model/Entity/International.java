package com.tfworkers.PDSISystem.Model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	 private String foriegnMinisterapprovel;
	 private int internoofOrganizations;
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
	 * Gets foriegn ministerapprovel.
	 *
	 * @return the foriegn ministerapprovel
	 */
	public String getForiegnMinisterapprovel() {
		return foriegnMinisterapprovel;
	}

	/**
	 * Sets foriegn ministerapprovel.
	 *
	 * @param foriegnMinisterapprovel the foriegn ministerapprovel
	 */
	public void setForiegnMinisterapprovel(String foriegnMinisterapprovel) {
		this.foriegnMinisterapprovel = foriegnMinisterapprovel;
	}

	/**
	 * Gets internoof organizations.
	 *
	 * @return the internoof organizations
	 */
	public int getInternoofOrganizations() {
		return internoofOrganizations;
	}

	/**
	 * Sets internoof organizations.
	 *
	 * @param internoofOrganizations the internoof organizations
	 */
	public void setInternoofOrganizations(int internoofOrganizations) {
		this.internoofOrganizations = internoofOrganizations;
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
