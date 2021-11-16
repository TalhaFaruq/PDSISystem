package com.tfworkers.PDSISystem.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.util.Date;

/**
 * The type Process.
 */
@Table(name = "process", indexes = {
		@Index(name = "created_date_index", columnList = "createdDate"),
		@Index(name = "active_index", columnList = "isActive")
})
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

}
