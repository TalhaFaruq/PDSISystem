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

import com.tfworkers.PDSISystem.Model.Entity.International;
import com.tfworkers.PDSISystem.Service.InternationalService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping("/international")
public class InternationalController {
	private final InternationalService internationalService;
	private final String na = "Not Authorize";

	/**
	 * InternationalController constructor
	 */
	public InternationalController(InternationalService internationalService) {
		this.internationalService = internationalService;
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
	 * @description This API get the international from database in ArrayList and shows it in
	 *              front end. With Authorization token.
	 * @createdTime 28 October 2021
	 */
	@GetMapping("all")
	public ResponseEntity<Object> listAllInternational(@RequestHeader("Authorization") String token) {

		if (authorization(token)) {
			return internationalService.listInternational();
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * @return ResponseEntity with object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the International from database in object and shows it in
	 *              front end. With Authorization token.
	 * @createdTime 29 October 2021
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getByInternationalID(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return internationalService.getInternationalbyid(id);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the International from front end in json. With Authorization
	 *              token.
	 * @createdTime 29 October 2021
	 */
	@PostMapping("/add")
	public ResponseEntity<Object> addInternational(@RequestHeader("Authorization") String token, @RequestBody International international) {
		if (authorization(token)) {
			return internationalService.saveInternational(international);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.OK);
	}

	/**
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API update the International in database. With Authorization
	 *              token.
	 * @createdTime 29 October 2021
	 */
	@PutMapping("/update")
	public ResponseEntity<Object> updateInternational(@RequestHeader("Authorization") String token, @RequestBody International international) {
		if (authorization(token)) {
			return internationalService.updateInternational(international);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API delete international in the database
	 * @createdTime 29 October 2021
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteInternational(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return internationalService.deleteInternational(id);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.OK);
	}


}
