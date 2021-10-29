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

import com.tfworkers.PDSISystem.Model.Entity.Project;
import com.tfworkers.PDSISystem.Service.ProjectService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping("/project")
public class ProjectController {
	private final ProjectService projectService;
	private final String na = "Not Authorize";

	/**
	 * ProjectController constructor
	 */
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
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
	 * @description This API get the projects from database in ArrayList and shows it in
	 *              front end. With Authorization token.
	 * @createdTime 28 October 2021
	 */
	@GetMapping("all")
	public ResponseEntity<Object> listAllProject(@RequestHeader("Authorization") String token) {

		if (authorization(token)) {
			return projectService.listProjects();
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * @return ResponseEntity with object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the Project from database in object and shows it in
	 *              front end. With Authorization token.
	 * @createdTime 29 October 2021
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getByProjectID(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return projectService.getProjectbyid(id);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the Project from front end in json. With Authorization
	 *              token.
	 * @createdTime 29 October 2021
	 */
	@PostMapping("/add")
	public ResponseEntity<Object> addProject(@RequestHeader("Authorization") String token, @RequestBody Project project) {
		if (authorization(token)) {
			return projectService.saveProject(project);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.OK);
	}

	/**
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API update the Project in database. With Authorization
	 *              token.
	 * @createdTime 29 October 2021
	 */
	@PutMapping("/update")
	public ResponseEntity<Object> updateProject(@RequestHeader("Authorization") String token, @RequestBody Project project) {
		if (authorization(token)) {
			return projectService.updateProject(project);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API delete project in the database
	 * @createdTime 29 October 2021
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteProject(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		if (authorization(token)) {
			return projectService.deleteProject(id);
		} else
			return new ResponseEntity<Object>(na, HttpStatus.OK);
	}

}
