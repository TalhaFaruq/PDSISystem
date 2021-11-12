package com.tfworkers.PDSISystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tfworkers.PDSISystem.Model.Entity.FuturePlan;
import com.tfworkers.PDSISystem.Service.FuturePlanService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Future plan controller.
 */
@EnableSwagger2
@RestController
@RequestMapping("/futurePlan")
public class FuturePlanController {
	private final FuturePlanService futurePlanService;
	private final String na = "Not Authorize";

	/**
	 * FuturePlanController constructor
	 *
	 * @param futurePlanService the future plan service
	 */
	public FuturePlanController(FuturePlanService futurePlanService) {
		this.futurePlanService = futurePlanService;
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
	 * List all future plan response entity.
	 *
	 * @param token the token
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the futurePlan from database in ArrayList and shows it in              front end. With Authorization token.
	 * @createdTime 28 October 2021
	 */
	@GetMapping("all")
	public ResponseEntity<Object> listAllFuturePlan(@RequestHeader("Authorization") String token) {

		if (authorization(token)) {
			return futurePlanService.listFuturePlan();
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Gets by future plan id.
	 *
	 * @param token the token
	 * @param id    the id
	 * @return ResponseEntity with object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the FuturePlan from database in object and shows it in              front end. With Authorization token.
	 * @createdTime 29 October 2021
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getByFuturePlanID(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return futurePlanService.getFuturePlanbyid(id);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Add future plan response entity.
	 *
	 * @param token      the token
	 * @param futurePlan the future plan
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the FuturePlan from front end in json. With Authorization              token.
	 * @createdTime 29 October 2021
	 */
	@PostMapping("/add")
	public ResponseEntity<Object> addFuturePlan(@RequestHeader("Authorization") String token, @RequestBody FuturePlan futurePlan) {
		if (authorization(token)) {
			return futurePlanService.saveFuturePlan(futurePlan);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.OK);
	}

	/**
	 * Update future plan response entity.
	 *
	 * @param token      the token
	 * @param futurePlan the future plan
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API update the FuturePlan in database. With Authorization              token.
	 * @createdTime 29 October 2021
	 */
	@PutMapping("/update")
	public ResponseEntity<Object> updateFuturePlan(@RequestHeader("Authorization") String token, @RequestBody FuturePlan futurePlan) {
		if (authorization(token)) {
			return futurePlanService.updateFuturePlan(futurePlan);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Delete future plan response entity.
	 *
	 * @param token the token
	 * @param id    the id
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API delete futurePlan in the database
	 * @createdTime 29 October 2021
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteFuturePlan(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return futurePlanService.deleteFuturePlan(id);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.OK);
	}


}
