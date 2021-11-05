package com.tfworkers.PDSISystem.Model.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Tags {
	 @Id
	 @Column(nullable = false)
	 @GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long tags_id;
	private String name;
	private Date createdDate;
	private Date updatedDate;
	private boolean isActive;


	public Long getTags_id() {
		return tags_id;
	}
	public void setTags_id(Long id) {
		this.tags_id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
