package com.tfworkers.PDSISystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tfworkers.PDSISystem.Model.Entity.National;
import com.tfworkers.PDSISystem.Service.NationalService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type National controller.
 */
@EnableSwagger2
@RestController
@RequestMapping("/national")
public class NationalController {

	private final NationalService nationalService;
	private final String na = "Not Authorize";

	/**
	 * NationalController constructor
	 *
	 * @param nationalService the national service
	 */
	public NationalController(NationalService nationalService) {
		this.nationalService = nationalService;
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
	 * List all national response entity.
	 *
	 * @param token the token
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the national from database in ArrayList and shows it in              front end. With Authorization token.
	 * @createdTime 28 October 2021
	 */
	@GetMapping("all")
	public ResponseEntity<Object> listAllNational(@RequestHeader("Authorization") String token) {

		if (authorization(token)) {
			return nationalService.listNational();
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Gets by national id.
	 *
	 * @param token the token
	 * @param id    the id
	 * @return ResponseEntity with object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the National from database in object and shows it in              front end. With Authorization token.
	 * @createdTime 29 October 2021
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getByNationalID(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return nationalService.getNationalbyid(id);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Add national response entity.
	 *
	 * @param token    the token
	 * @param national the national
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the National from front end in json. With Authorization              token.
	 * @createdTime 29 October 2021
	 */
	@PostMapping("/add")
	public ResponseEntity<Object> addNational(@RequestHeader("Authorization") String token, @RequestBody National national) {
		if (authorization(token)) {
			return nationalService.saveNational(national);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.OK);
	}

	/**
	 * Update national response entity.
	 *
	 * @param token    the token
	 * @param national the national
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API update the National in database. With Authorization              token.
	 * @createdTime 29 October 2021
	 */
	@PutMapping("/update")
	public ResponseEntity<Object> updateNational(@RequestHeader("Authorization") String token, @RequestBody National national) {
		if (authorization(token)) {
			return nationalService.updateNational(national);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Delete national response entity.
	 *
	 * @param token the token
	 * @param id    the id
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API delete national in the database
	 * @createdTime 29 October 2021
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteNational(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return nationalService.deleteNational(id);
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
