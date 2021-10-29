package com.tfworkers.PDSISystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfworkers.PDSISystem.Model.Entity.FuturePlan;
import com.tfworkers.PDSISystem.Service.FuturePlanService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping("/futurePlan")
public class FuturePlanController {
	private final FuturePlanService futurePlanService;
	private final String na = "Not Authorize";

	/**
	 * FuturePlanController constructor
	 */
	public FuturePlanController(FuturePlanService futurePlanService) {
		this.futurePlanService = futurePlanService;
	}

	/**
	 * This is token for checking authorization
	 */
	private String key = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

	/**
	 * Authorization function
	 */
	public Boolean authorization(String token) {
		return key.equals(token);
	}

	/**
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the futurePlan from database in ArrayList and shows it in
	 *              front end. With Authorization token.
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
	 * @return ResponseEntity with object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the FuturePlan from database in object and shows it in
	 *              front end. With Authorization token.
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
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the FuturePlan from front end in json. With Authorization
	 *              token.
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
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API update the FuturePlan in database. With Authorization
	 *              token.
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
