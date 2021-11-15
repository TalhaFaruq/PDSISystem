package com.tfworkers.PDSISystem.Controller;

import com.lowagie.text.DocumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tfworkers.PDSISystem.Model.Entity.Project;
import com.tfworkers.PDSISystem.Service.ProjectService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Project controller.
 */
@EnableSwagger2
@RestController
@RequestMapping("/project")
public class ProjectController {
	private final ProjectService projectService;
	private final String na = "Not Authorize";

	/**
	 * ProjectController constructor
	 *
	 * @param projectService the project service
	 */
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
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
	 * List all project response entity.
	 *
	 * @param token the token
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the projects from database in ArrayList and shows it in              front end. With Authorization token.
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
	 * Gets by project id.
	 *
	 * @param token the token
	 * @param id    the id
	 * @return ResponseEntity with object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the Project from database in object and shows it in              front end. With Authorization token.
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
	 * Add project response entity.
	 *
	 * @param token   the token
	 * @param project the project
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This API get the Project from front end in json. With Authorization              token.
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
	 * Update project response entity.
	 *
	 * @param token   the token
	 * @param project the project
	 * @return Just returns ResponseEntity
	 * @author Talha Farooq
	 * @version 0.3
	 * @description This API update the Project in database. With Authorization              token.
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
	 * Delete project response entity.
	 *
	 * @param token the token
	 * @param id    the id
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

	/**
	 * Export to pdf.
	 *
	 * @param response the response
	 * @throws DocumentException the document exception
	 * @throws IOException       the io exception
	 */
	@GetMapping("/projectReport")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		projectService.projectExportToPDF(response);
	}

	/**
	 * Checks the timeline of projects from users and send them email to notify about end date with warning
	 * after three warnings the manager or officer can not be able to submit report.
	 *
	 * @param token the token
	 * @return the response entity
	 */
	@GetMapping("/endDateProject")
	public ResponseEntity<Object> endDateProject(@RequestHeader("Authorization") String token){
		if (authorization(token)) {
			projectService.projectEndDate();
			return new ResponseEntity<>("sent",HttpStatus.OK);
		} else
			return new ResponseEntity<>(na, HttpStatus.OK);
	}

}
