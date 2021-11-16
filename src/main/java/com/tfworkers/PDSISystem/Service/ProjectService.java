package com.tfworkers.PDSISystem.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lowagie.text.DocumentException;
import com.tfworkers.PDSISystem.Model.Entity.User;
import com.tfworkers.PDSISystem.Repository.UserRepository;
import com.tfworkers.PDSISystem.Utilities.EmailUtil;
import com.tfworkers.PDSISystem.Utilities.ProjectsPDFExporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.Project;
import com.tfworkers.PDSISystem.Repository.ProjectRepository;

import javax.servlet.http.HttpServletResponse;

/**
 * The type Project service.
 */
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    @Autowired
    private final UserRepository userRepository;


    private final EmailUtil emailUtil;

    /**
     * Constructor
     *
     * @param projectRepository the project repository
     * @param emailUtil         the email util
     * @param userRepository    the user repository
     */
    public ProjectService(ProjectRepository projectRepository, EmailUtil emailUtil, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.emailUtil = emailUtil;
        this.userRepository = userRepository;
    }

    private static final Logger logger = LogManager.getLogger(ProcessService.class);

    /**
     * List projects response entity.
     *
     * @return ResponseEntity which return list of projects. and in else it just return not found status
     * @author Talha Farooq
     * @version 0.1
     * @description This function get and show all the projects which are saved in database. The data from database comes in list.
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> listProjects() {
        try {
            List<Project> projectList = projectRepository.findAllByIsActiveOrderByCreatedDate(true);
            if (!projectList.isEmpty()) {
                logger.info("Showing projects Service Class");
                return ResponseEntity.ok().body(projectList);
            } else
                return new ResponseEntity<>("List Empty", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Save project response entity.
     *
     * @param project the project
     * @return responseEntity Status and projects object
     * @author Talha Farooq
     * @version 0.1
     * @description Save projects into database by getting values from controller and set date/time
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> saveProject(Project project) {
        try {
            Calendar date = Calendar.getInstance();
            project.setCreatedDate(date.getTime());
            projectRepository.save(project);
            logger.info("Saving project Service class", project);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update project response entity.
     *
     * @param project the project
     * @return only responseEntity Status and project
     * @author Talha Farooq
     * @version 0.1
     * @description update project into database by getting values from controller and set date/time
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> updateProject(Project project) {
        try {
            Calendar date = Calendar.getInstance();
            project.setUpdatedDate(date.getTime());
            projectRepository.save(project);
            logger.info("Updating project Service class");
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete project response entity.
     *
     * @param id the id
     * @return ResponseEntity response entity
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
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets projectbyid.
     *
     * @param id the id
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
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Checks the end date of project from timeline and send users email which are associated with them.
     */
    public ResponseEntity<Object> projectEndDate() {
        try {
            notifyUserAfterEndDateOfProject();
            return new ResponseEntity<>("Email sent to Users who are managers or officers " +
                    "who have not submitted report on before end time", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public void notifyUserAfterEndDateOfProject() {
        List<User> userList = userRepository.findAllByIsActiveOrderByCreatedDate(true);
        Calendar date = Calendar.getInstance();
        userList.stream()
                .flatMap(user -> user.getProjects().stream()
                        .flatMap(project -> project.getTimelines().stream()
                                .filter(timeline -> {
                                    if (timeline.getEndDate().after(date.getTime()) && user.getWarning() <= 3) {
                                        user.setWarning((1 + user.getWarning()));
                                        userRepository.save(user);
                                        emailUtil.sendMail(user.getEmail(), "Warning",
                                                user.getFirstName() + " end date of " + project.getName() + " timeline " + timeline.getName() + " has passed please submit your report"
                                                        + " this is your warning " + user.getWarning() + " if warning exceed 3 you will be forced logged out");
                                        return true;
                                    } else return false;
                                })
                        )).collect(Collectors.toList());
    }

    /**
     * Project export to pdf.
     *
     * @param response the response
     * @throws DocumentException the document exception
     * @throws IOException       the io exception
     */
    public void projectExportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);


        List<Project> projects = projectRepository.findAll();
        ProjectsPDFExporter exporter = new ProjectsPDFExporter(projects);
        exporter.export(response);
    }

}
