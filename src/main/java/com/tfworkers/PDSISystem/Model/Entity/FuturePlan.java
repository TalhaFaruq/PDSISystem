package com.tfworkers.PDSISystem.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.util.Date;

/**
 * The type Future plan.
 */
@Table(name = "futureplan", indexes = {
		@Index(name = "created_date_index", columnList = "createdDate"),
		@Index(name = "active_index", columnList = "isActive")
})
@Entity
@Data
public class FuturePlan {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long future_id;
	@NotBlank(message = "Name is Mandatory")
	private String name;
	private String description;
	private String planType;
	private Date createdDate;
	private Date updatedDate;
	private boolean isActive = true;
}
