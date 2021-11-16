package com.tfworkers.PDSISystem.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

import java.util.Date;

/**
 * The type National.
 */
@Table(name = "national", indexes = {
		@Index(name = "created_date_index", columnList = "createdDate"),
		@Index(name = "active_index", columnList = "isActive")
})
@Entity
@Data
public class National {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long national_id;
	@NotNull(message = "Number of Organization are Mandatory")
	private int noofOrganization;
	private String location;
	private int land;
	private Date createdDate;
	private Date updatedDate;
	private boolean isActive = true;
}
