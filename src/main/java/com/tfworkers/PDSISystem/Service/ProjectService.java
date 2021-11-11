package com.tfworkers.PDSISystem.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.lowagie.text.DocumentException;
import com.tfworkers.PDSISystem.Utilities.EmailUtil;
import com.tfworkers.PDSISystem.Utilities.ProjectsPDFExporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.Project;
import com.tfworkers.PDSISystem.Repository.ProjectRepository;

import javax.servlet.http.HttpServletResponse;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private final EmailUtil emailUtil;

    /**
     * Constructor
     */
    public ProjectService(ProjectRepository projectRepository, EmailUtil emailUtil) {
        this.projectRepository = projectRepository;
        this.emailUtil = emailUtil;
    }

    private static final Logger logger = LogManager.getLogger(UserService.class);

    /**
     * @return ResponseEntity which return list of projects. and in else it just return
     * not found status
     * @author Talha Farooq
     * @version 0.1
     * @description This function get and show all the projects which are saved in
     * database. The data from database comes in list.
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> listProjects() {
        try {
            List<Project> projectList = projectRepository.findByOrderByCreatedDateAsc();
            if (!projectList.isEmpty()) {
                logger.info("Showing projects Service Class");
                return ResponseEntity.ok().body(projectList);
            } else
                return new ResponseEntity<Object>("List Empty", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return responseEntity Status and projects object
     * @author Talha Farooq
     * @version 0.1
     * @description Save projects into database by getting values from controller and
     * set date/time
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
     * set date/time
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

//    public ResponseEntity<Object> projectEndDate() {
//        try {
//            List<Project> projectList = projectRepository.findByProjectStatus(false);
//            LocalDateTime ldt = LocalDateTime.now();
//            Instant instant = ldt.toInstant(ZoneOffset.UTC);
//            Date date = Date.from(instant);
//            for (Project projects : projectList) {
//                if (!projects.getTimelines().isEmpty()) {
//                    List<Timeline> timeline =  projects.getTimelines();
//                    for(Timeline timelines = timeline){
//                        Date endDate = timelines.getEndDate();
//                        if(endDate.after(date)){
//                            String message = "Timeline has passed please submit your report";
//                            emailUtil.sendMail(user.get().getEmail(), "Warning", message);
//                        }
//                    }
//
//                    return new ResponseEntity<>("Sent Warnings", HttpStatus.OK);
//                }
//            }
//            return new ResponseEntity<>("Timelines does not Exist", HttpStatus.OK);
//        } catch (Exception e) {
//            System.out.println(e);
//            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
//
//        }
//    }

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
