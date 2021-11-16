package com.tfworkers.PDSISystem.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.util.Date;

/**
 * The type International.
 */
@Table(name = "international", indexes = {
		@Index(name = "created_date_index", columnList = "createdDate"),
		@Index(name = "active_index", columnList = "isActive")
})
@Entity
@Data
public class International {
	 @Id
	 @Column(nullable = false)
	 @GeneratedValue(strategy = GenerationType.IDENTITY )
	 private Long international_id;
	 @NotBlank(message = "Status Approval 'Write approved or not approved'")
	 private String foreignMinisterApproval;
	 private int internOfOrganizations;
	 private Date createdDate;
 	 private Date updatedDate;
	 private boolean isActive;

}
