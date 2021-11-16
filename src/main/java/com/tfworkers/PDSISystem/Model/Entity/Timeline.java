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
@Table(name = "timeline", indexes = {
		@Index(name = "created_date_index", columnList = "createdDate"),
		@Index(name = "active_index", columnList = "isActive")
})
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
	
	
	@OneToMany(targetEntity = Process.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	 private List<Process> processes = new ArrayList<>();

}
