package com.tfworkers.PDSISystem.Service;


import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.Budget;
import com.tfworkers.PDSISystem.Repository.BudgetRepository;


/**
 * The type Budget service.
 */
@Service
public class BudgetService {
    private BudgetRepository budgetRepository;

    /**
     * Constructor
     *
     * @param budgetRepository the budget repository
     */
    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    private static final Logger logger = LogManager.getLogger(BudgetService.class);

    /**
     * List budget response entity.
     *
     * @return ResponseEntity which return list of budget. and in else it just         return not found status
     * @author Talha Farooq
     * @version 0.1
     * @description This function get and show all the budget which are saved in              database. The data from database comes in list.
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> listBudget() {
        try {
            List<Budget> budgetList = budgetRepository.findAllByIsActiveOrderByCreatedDate(true);
            if (!budgetList.isEmpty()) {
                logger.info("Getting Budget List");
                return ResponseEntity.ok().body(budgetList);
            } else
                return new ResponseEntity<>("List Empty", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage(), "Error in getting budget list");
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Save budget response entity.
     *
     * @param budget the budget
     * @return responseEntity Status and budget object
     * @author Talha Farooq
     * @version 0.1
     * @description Save projects into database by getting values from controller              and set date/time
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> saveBudget(Budget budget) {
        try {
            Calendar date = Calendar.getInstance();
            budget.setCreatedDate(date.getTime());
            budgetRepository.save(budget);
            logger.info("Saving Budget Object");
            return new ResponseEntity<>(budget, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), "Error saving Budget Object");
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update budget response entity.
     *
     * @param budget the budget
     * @return only responseEntity Status and budget
     * @author Talha Farooq
     * @version 0.1
     * @description update budget into database by getting values from controller              and set date/time
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> updateBudget(Budget budget) {
        try {
            Calendar date = Calendar.getInstance();
            budget.setUpdatedDate(date.getTime());
            budgetRepository.save(budget);
            logger.info("Budget updated");
            return new ResponseEntity<Object>(budget, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), "Error in updating Budget");
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete budget response entity.
     *
     * @param id the id
     * @return ResponseEntity response entity
     * @author Talha Farooq
     * @description Hide budget in database
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> deleteBudget(Long id) {
        try {
            Optional<Budget> budget = budgetRepository.findById(id);
            budget.get().setActive(false);
            budgetRepository.save(budget.get());
            logger.info("Budget Active status false");
            return new ResponseEntity<>(budget, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), "Error Deleting Budget");
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets budgetbyid.
     *
     * @param id the id
     * @return ResponseEntity with one object of budget
     * @author Talha Farooq
     * @version 0.1
     * @description Find by ID budget from database
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> getBudgetbyid(Long id) {
        try {
            Optional<Budget> budget = budgetRepository.findById(id);
            logger.info("Getting Budget by ID");
            return ResponseEntity.ok().body(budget.get());
        } catch (Exception e) {
            logger.error(e.getMessage(), "Error Getting by id Budget");
            System.out.println(e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
