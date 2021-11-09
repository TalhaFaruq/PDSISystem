package com.tfworkers.PDSISystem.Model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

import java.util.Date;

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
	public Long getNational_id() {
		return national_id;
	}
	public void setNational_id(Long id) {
		this.national_id = id;
	}
	public int getNoofOrganization() {
		return noofOrganization;
	}
	public void setNoofOrganization(int noofOrganization) {
		this.noofOrganization = noofOrganization;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getLand() {
		return land;
	}
	public void setLand(int land) {
		this.land = land;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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


}
