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
	public Long getInternational_id() {
		return international_id;
	}
	public void setInternational_id(Long id) {
		this.international_id = id;
	}
	public String getForiegnMinisterapprovel() {
		return foriegnMinisterapprovel;
	}
	public void setForiegnMinisterapprovel(String foriegnMinisterapprovel) {
		this.foriegnMinisterapprovel = foriegnMinisterapprovel;
	}
	public int getInternoofOrganizations() {
		return internoofOrganizations;
	}
	public void setInternoofOrganizations(int internoofOrganizations) {
		this.internoofOrganizations = internoofOrganizations;
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
	 
	 
	 

}
