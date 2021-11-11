package com.tfworkers.PDSISystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tfworkers.PDSISystem.Model.Entity.Budget;
import com.tfworkers.PDSISystem.Service.BudgetService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Budget controller.
 */
@EnableSwagger2
@RestController
@RequestMapping("/budget")
public class BudgetController {
	private final BudgetService budgetService;
	private final String na = "Not Authorize";

	/**
	 * BudgetController constructor
	 *
	 * @param budgetService the budget service
	 */
	public BudgetController(BudgetService budgetService) {
		this.budgetService = budgetService;
	}

	/**
	 * This is token for checking authorization
	 */
	private String key = "12345";

	/**
	 * Authorization function
	 *
	 * @param token the token
	 * @return the boolean
	 */
	public Boolean authorization(String token) {
		return key.equals(token);
	}

	/**
	 * List all budget response entity.
	 *
	 * @param token the token
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the budget from database in ArrayList and shows it in              front end. With Authorization token.
	 * @createdTime 28 October 2021
	 */
	@GetMapping("all")
	public ResponseEntity<Object> listAllBudget(@RequestHeader("Authorization") String token) {

		if (authorization(token)) {
			return budgetService.listBudget();
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Gets by budget id.
	 *
	 * @param token the token
	 * @param id    the id
	 * @return ResponseEntity with object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the Budget from database in object and shows it in              front end. With Authorization token.
	 * @createdTime 29 October 2021
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getByBudgetID(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return budgetService.getBudgetbyid(id);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Add budget response entity.
	 *
	 * @param token  the token
	 * @param budget the budget
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the Budget from front end in json. With Authorization              token.
	 * @createdTime 29 October 2021
	 */
	@PostMapping("/add")
	public ResponseEntity<Object> addBudget(@RequestHeader("Authorization") String token, @RequestBody Budget budget) {
		if (authorization(token)) {
			return budgetService.saveBudget(budget);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.OK);
	}

	/**
	 * Update budget response entity.
	 *
	 * @param token  the token
	 * @param budget the budget
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API update the Budget in database. With Authorization              token.
	 * @createdTime 29 October 2021
	 */
	@PutMapping("/update")
	public ResponseEntity<Object> updateBudget(@RequestHeader("Authorization") String token, @RequestBody Budget budget) {
		if (authorization(token)) {
			return budgetService.updateBudget(budget);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Delete budget response entity.
	 *
	 * @param token the token
	 * @param id    the id
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API delete budget in the database
	 * @createdTime 29 October 2021
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteBudget(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return budgetService.deleteBudget(id);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.OK);
	}

	/**
	 * Input validation exception response entity.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	public ResponseEntity<Object> inputValidationException(Exception e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

	}


}
