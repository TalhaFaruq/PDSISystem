package com.tfworkers.PDSISystem.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * The type Tags.
 */
@Table(name = "tags", indexes = {
		@Index(name = "created_date_index", columnList = "createdDate"),
		@Index(name = "active_index", columnList = "isActive")
})
@Entity
@Data
public class Tags {
	 @Id
	 @Column(nullable = false)
	 @GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long tags_id;
	@NotBlank(message = "Name is Mandatory")
	private String name;
	private Date createdDate;
	private Date updatedDate;
	private boolean isActive = true;

}
