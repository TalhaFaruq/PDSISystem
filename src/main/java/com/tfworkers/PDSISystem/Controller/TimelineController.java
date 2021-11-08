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

import com.tfworkers.PDSISystem.Model.Entity.Timeline;
import com.tfworkers.PDSISystem.Service.TimelineService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping("/timeline")
public class TimelineController {
	private final TimelineService timelineService;
	private final String na = "Not Authorize";

	/**
	 * TimelineController constructor
	 */
	public TimelineController(TimelineService timelineService) {
		this.timelineService = timelineService;
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
	 * @description This API get the time line from database in ArrayList and shows
	 *              it in front end. With Authorization token.
	 * @createdTime 28 October 2021
	 */
	@GetMapping("all")
	public ResponseEntity<Object> listAllTimeline(@RequestHeader("Authorization") String token) {
		if (authorization(token)) {
			return timelineService.listAllTimeline();
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * @return ResponseEntity with object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the time line from database in object and shows it
	 *              in front end. With Authorization token.
	 * @createdTime 28 October 2021
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getByTimelineID(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return timelineService.getTimelineId(id);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the time line from front end in json. With
	 *              Authorization token.
	 * @createdTime 28 October 2021
	 */
	@PostMapping("/add")
	public ResponseEntity<Object> addTimeline(@RequestHeader("Authorization") String token,
			@RequestBody Timeline timeline) {
		if (authorization(token)) {
			return timelineService.saveTimeline(timeline);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.OK);
	}

	/**
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API update the time line in database. With Authorization
	 *              token.
	 * @createdTime 28 October 2021
	 */
	@PutMapping("/update")
	public ResponseEntity<Object> updateTimeline(@RequestHeader("Authorization") String token,
			@RequestBody Timeline timeline) {
		if (authorization(token)) {
			return timelineService.updateTimeline(timeline);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API delete certain time line in the database
	 * @createdTime 5 October 2021
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteTimeline(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return timelineService.deleteTimeline(id);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.OK);
	}

}
