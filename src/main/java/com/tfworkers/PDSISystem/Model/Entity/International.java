package com.tfworkers.PDSISystem.Model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class International {
	 @Id
	 @Column(nullable = false)
	 @GeneratedValue(strategy = GenerationType.IDENTITY )
	 private Long id;
	 private String foriegnMinisterapprovel;
	 private int internoofOrganizations;
	 private String createdDate;
 	 private String updatedDate;
	 private boolean isActive;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	 
	 
	 

}
