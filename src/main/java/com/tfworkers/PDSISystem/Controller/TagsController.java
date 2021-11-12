package com.tfworkers.PDSISystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tfworkers.PDSISystem.Model.Entity.Tags;
import com.tfworkers.PDSISystem.Service.TagsService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Tags controller.
 */
@EnableSwagger2
@RestController
@RequestMapping("/tags")
public class TagsController {
	private final TagsService tagsService;
	private final String na = "Not Authorize";

	/**
	 * TagsController constructor
	 *
	 * @param tagsService the tags service
	 */
	public TagsController(TagsService tagsService) {
		this.tagsService = tagsService;
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
	 * List all tags response entity.
	 *
	 * @param token the token
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the tags from database in ArrayList and shows it in              front end. With Authorization token.
	 * @createdTime 28 October 2021
	 */
	@GetMapping("all")
	public ResponseEntity<Object> listAllTags(@RequestHeader("Authorization") String token) {

		if (authorization(token)) {
			return tagsService.listTags();
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Gets by tags id.
	 *
	 * @param token the token
	 * @param id    the id
	 * @return ResponseEntity with object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the tags from database in object and shows it in              front end. With Authorization token.
	 * @createdTime 28 October 2021
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getByTagsID(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return tagsService.getTagbyid(id);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Add tags response entity.
	 *
	 * @param token the token
	 * @param Tags  the tags
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the tags from front end in json. With Authorization              token.
	 * @createdTime 28 October 2021
	 */
	@PostMapping("/add")
	public ResponseEntity<Object> addTags(@RequestHeader("Authorization") String token, @RequestBody Tags Tags) {
		if (authorization(token)) {
			return tagsService.saveTags(Tags);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.OK);
	}

	/**
	 * Update tags response entity.
	 *
	 * @param token the token
	 * @param Tags  the tags
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API update the time line in database. With Authorization              token.
	 * @createdTime 28 October 2021
	 */
	@PutMapping("/update")
	public ResponseEntity<Object> updateTags(@RequestHeader("Authorization") String token, @RequestBody Tags Tags) {
		if (authorization(token)) {
			return tagsService.updateTags(Tags);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Delete tags response entity.
	 *
	 * @param token the token
	 * @param id    the id
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API delete tag in the database
	 * @createdTime 5 October 2021
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteTags(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return tagsService.deleteTags(id);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.OK);
	}

}
