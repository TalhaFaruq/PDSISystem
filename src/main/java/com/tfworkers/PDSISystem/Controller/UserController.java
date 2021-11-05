package com.tfworkers.PDSISystem.Controller;

import javax.mail.MessagingException;

import com.tfworkers.PDSISystem.Utilities.UserPDFExporter;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tfworkers.PDSISystem.Model.Entity.User;
import com.tfworkers.PDSISystem.Service.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	private final String na = "Not Authorize";

	/**
	 * UserContorller constructor
	 */
	public UserController(UserService userService) {
		this.userService = userService;
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
	 * Login it takes email and password from front end then check from database by
	 * calling object with email
	 */
	@GetMapping("/login")
	public ResponseEntity<Object> login(@RequestParam("email") String email,
			@RequestParam("password") String password) {
		if (userService.findByEmailandPassword(email, password)) {
			return new ResponseEntity<Object>("Logged in", HttpStatus.OK);
		} else
			return new ResponseEntity<Object>("Email not Exist", HttpStatus.OK);
	}

	/**
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the user from database in ArrayList and shows it in
	 *              front end. With Authorization token.
	 * @createdTime 28 October 2021
	 */
	@GetMapping("all")
	public ResponseEntity<Object> listAllUsers(@RequestHeader("Authorization") String token) {
		if (authorization(token)) {
			return userService.listallUsers();
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * @return ResponseEntity with object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the user from database in object and shows it in
	 *              front end. With Authorization token.
	 * @createdTime 28 October 2021
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getByUserID(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return userService.getUserbyid(id);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the user from front end in json. With Authorization
	 *              token.
	 * @createdTime 28 October 2021
	 */
	@PostMapping("/add")
	public ResponseEntity<Object> addUser(@RequestHeader("Authorization") String token, @RequestBody User user) {
		if (authorization(token)) {
			return userService.saveUser(user);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.OK);
	}

	/**
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API update the user in database. With Authorization token.
	 * @createdTime 28 October 2021
	 */
	@PutMapping("/update")
	public ResponseEntity<Object> updateUser(@RequestHeader("Authorization") String token, @RequestBody User user) {
		if (authorization(token)) {
			return userService.updateUser(user);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API delete certain user in the database
	 * @createdTime 5 October 2021
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteUser(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return userService.deleteUser(id);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.OK);
	}

	/**
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @throws MessagingException 
	 * @description This API create message and email with token
	 * @createdTime 5 October 2021
	 */
	@PutMapping("/email/{userId}")
	public ResponseEntity<Object> email(@RequestHeader("Authorization") String token,
			@PathVariable(value = "userId") Long userId) throws MessagingException {
		if (authorization(token)) {
			return userService.tokensendemailandsms(userId);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	@PutMapping("/verify/{userId}")
	public ResponseEntity<Object> verification(@RequestHeader("Authorization") String token,
											   @RequestHeader("emailToken") int emailtoken,
											   @RequestHeader("email") String email) {
		if (authorization(token)) {
			return userService.verificationSmsAndEmail(emailtoken,email);
		}else return new ResponseEntity(na, HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/pdf/download")
	public ResponseEntity<Object> pdfdownload() {
		UserPDFExporter userPDFExporter = null;

		return null;
	}

	@GetMapping("/recommendedManagers")
	public ResponseEntity<Object> showRecommendedManagers(@RequestHeader("tag") String tag ){
		return userService.recommendedManagers(tag);
	}
}
