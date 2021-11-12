package com.tfworkers.PDSISystem.Model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

import java.util.Date;

/**
 * The type Budget.
 */
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

	/**
	 * Gets budget id.
	 *
	 * @return the budget id
	 */
	public Long getBudget_id() {
		return budget_id;
	}

	/**
	 * Sets budget id.
	 *
	 * @param id the id
	 */
	public void setBudget_id(Long id) {
		this.budget_id = id;
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets quantity.
	 *
	 * @return the quantity
	 */
	public long getQuantity() {
		return quantity;
	}

	/**
	 * Sets quantity.
	 *
	 * @param quantity the quantity
	 */
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets budget.
	 *
	 * @return the budget
	 */
	public long getBudget() {
		return budget;
	}

	/**
	 * Sets budget.
	 *
	 * @param budget the budget
	 */
	public void setBudget(long budget) {
		this.budget = budget;
	}

	/**
	 * Gets created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets created date.
	 *
	 * @param createdDate the created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets updated date.
	 *
	 * @return the updated date
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * Sets updated date.
	 *
	 * @param updatedDate the updated date
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Is active boolean.
	 *
	 * @return the boolean
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Sets active.
	 *
	 * @param isActive the is active
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
}
