package com.tfworkers.PDSISystem.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.Budget;
import com.tfworkers.PDSISystem.Repository.BudgetRepository;

@Service
public class BudgetService {
	private BudgetRepository budgetRepository;

	/**
	 * Constructor
	 */
	public BudgetService(BudgetRepository budgetRepository) {
		this.budgetRepository = budgetRepository;
	}

	/**
	 * @return ResponseEntity which return list of budget. and in else it just
	 *         return not found status
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This function get and show all the budget which are saved in
	 *              database. The data from database comes in list.
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> listBudget() {
		try {
			List<Budget> budgetList = budgetRepository.findAll();
			if (!budgetList.isEmpty()) {
				return ResponseEntity.ok().body(budgetList);
			} else
				return new ResponseEntity<Object>("List Empty", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot access List of projects from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return responseEntity Status and budget object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Save projects into database by getting values from controller
	 *              and set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> saveBudget(Budget budget) {
		try {
			Calendar date = Calendar.getInstance();
			budget.setCreatedDate(date.getTime());
			budgetRepository.save(budget);
			return new ResponseEntity<Object>(budget, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot save values in database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return only responseEntity Status and budget
	 * @author Talha Farooq
	 * @version 0.1
	 * @description update budget into database by getting values from controller
	 *              and set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> updateBudget(Budget budget) {
		try {
			Calendar date = Calendar.getInstance();
			budget.setUpdatedDate(date.getTime());
			budgetRepository.save(budget);
			return new ResponseEntity<Object>(budget, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot update the budget into database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return ResponseEntity
	 * @author Talha Farooq
	 * @description Hide budget in database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> deleteBudget(Long id) {
		try {
			Optional<Budget> budget = budgetRepository.findById(id);
			budget.get().setActive(false);
			budgetRepository.save(budget.get());
			return new ResponseEntity<Object>(budget, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot Access certain project id from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return ResponseEntity with one object of budget
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Find by ID budget from database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> getBudgetbyid(Long id) {
		try {
			Optional<Budget> budget = budgetRepository.findById(id);
			return ResponseEntity.ok().body(budget.get());
		} catch (Exception e) {
			return new ResponseEntity<Object>("Budget does not Exist in database", HttpStatus.NOT_FOUND);
		}
	}

}
