package com.tfworkers.PDSISystem.Model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

import java.util.Date;

/**
 * The type National.
 */
@Entity
@Data
public class National {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long national_id;
	private int noofOrganization;
	private String location;
	private int land;
	private Date createdDate;
	private Date updatedDate;
	private boolean isActive;

	/**
	 * Gets national id.
	 *
	 * @return the national id
	 */
	public Long getNational_id() {
		return national_id;
	}

	/**
	 * Sets national id.
	 *
	 * @param id the id
	 */
	public void setNational_id(Long id) {
		this.national_id = id;
	}

	/**
	 * Gets noof organization.
	 *
	 * @return the noof organization
	 */
	public int getNoofOrganization() {
		return noofOrganization;
	}

	/**
	 * Sets noof organization.
	 *
	 * @param noofOrganization the noof organization
	 */
	public void setNoofOrganization(int noofOrganization) {
		this.noofOrganization = noofOrganization;
	}

	/**
	 * Gets location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets location.
	 *
	 * @param location the location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets land.
	 *
	 * @return the land
	 */
	public int getLand() {
		return land;
	}

	/**
	 * Sets land.
	 *
	 * @param land the land
	 */
	public void setLand(int land) {
		this.land = land;
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
