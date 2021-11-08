package com.tfworkers.PDSISystem.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

	private static final Logger logger = LogManager.getLogger(UserService.class);

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
				logger.info("Showing projects Service Class");
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
			Calendar date = Calendar.getInstance();
			project.setCreatedDate(date.getTime());
			projectRepository.save(project);
			logger.info("Saving project Service class");
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
			Calendar date = Calendar.getInstance();
			project.setUpdatedDate(date.getTime());
			projectRepository.save(project);
			logger.info("Updating project Service class");
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
			logger.info("Deleting project service class");
			return new ResponseEntity<>(project, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cannot Access certain project id from database", HttpStatus.NOT_FOUND);
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
			logger.info("Getting project by ID");
			return ResponseEntity.ok().body(project.get());
		} catch (Exception e) {
			return new ResponseEntity<>("project does not Exist in database", HttpStatus.NOT_FOUND);
		}
	}

}
