package com.tfworkers.PDSISystem.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.Project;
import com.tfworkers.PDSISystem.Repository.ProjectRepository;

@Service
public class ProjectService {

	private ProjectRepository projectRepository;

	/**
	 * Constructor
	 */
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	/**
	 * @return ResponseEntity which return list of projects. and in else it just return
	 *         not found status
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This function get and show all the projects which are saved in
	 *              database. The data from database comes in list.
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> listProjects() {
		try {
			List<Project> projectList = projectRepository.findAll();
			if (!projectList.isEmpty()) {
				return ResponseEntity.ok().body(projectList);
			} else
				return new ResponseEntity<Object>("List Empty", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot access List of projects from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return responseEntity Status and projects object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Save projects into database by getting values from controller and
	 *              set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> saveProject(Project project) {
		try {
			LocalDateTime date = LocalDateTime.now();
			project.setCreatedDate(date.toString());
			projectRepository.save(project);
			return new ResponseEntity<Object>(project, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot save values in database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return only responseEntity Status and project
	 * @author Talha Farooq
	 * @version 0.1
	 * @description update project into database by getting values from controller and
	 *              set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> updateProject(Project project) {
		try {
			LocalDateTime date = LocalDateTime.now();
			project.setUpdatedDate(date.toString());
			projectRepository.save(project);
			return new ResponseEntity<Object>(project, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot update the project into database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return ResponseEntity
	 * @author Talha Farooq
	 * @description Hide project in database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> deleteProject(Long id) {
		try {
			Optional<Project> project = projectRepository.findById(id);
			project.get().setActive(false);
			projectRepository.save(project.get());
			return new ResponseEntity<Object>(project, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot Access certain project id from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return ResponseEntity with one object of project
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Find by ID project from database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> getProjectbyid(Long id) {
		try {
			Optional<Project> project = projectRepository.findById(id);
			return ResponseEntity.ok().body(project.get());
		} catch (Exception e) {
			return new ResponseEntity<Object>("project does not Exist in database", HttpStatus.NOT_FOUND);
		}
	}

}
