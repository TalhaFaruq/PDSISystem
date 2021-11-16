package com.tfworkers.PDSISystem.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

import java.util.Date;

/**
 * The type Budget.
 */
@Table(name = "budgets", indexes = {
		@Index(name = "created_date_index", columnList = "createdDate"),
		@Index(name = "active_index", columnList = "isActive")
})
@Entity
@Data
public class Budget {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long budget_id;
	@NotBlank(message = "Name is Mandatory")
	private String name;
	@Min(value=1, message = "Quantity should be greater than 0")
	@NotNull(message = "Quantity of the product is Mandatory")
	private long quantity;
	@Min(value=1, message = "Budget should be greater than 0")
	@NotNull(message = "Budget of the product is Mandatory")
	private long budget;
	private Date createdDate;
	private Date updatedDate;
	private boolean isActive = true;
}
