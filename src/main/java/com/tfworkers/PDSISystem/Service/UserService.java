package com.tfworkers.PDSISystem.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import com.lowagie.text.DocumentException;
import com.tfworkers.PDSISystem.Model.DTO.RecommendedManagerDTO;

import com.tfworkers.PDSISystem.Model.Entity.Project;
import com.tfworkers.PDSISystem.Repository.ProjectRepository;
import com.tfworkers.PDSISystem.Utilities.UserPDFExporter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.SMS;
import com.tfworkers.PDSISystem.Model.Entity.User;
import com.tfworkers.PDSISystem.Repository.UserRepository;
import com.tfworkers.PDSISystem.Utilities.EmailUtil;
import com.tfworkers.PDSISystem.Utilities.SmsUtil;

import javax.servlet.http.HttpServletResponse;

/**
 * The type User service.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final EmailUtil emailUtil;
    private final SmsUtil smsUtil;


    /**
     * Constructor
     *
     * @param userRepository    the user repository
     * @param projectRepository the project repository
     * @param emailUtil         the email util
     * @param smsUtil           the sms util
     */
    public UserService(UserRepository userRepository, ProjectRepository projectRepository, EmailUtil emailUtil, SmsUtil smsUtil) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.emailUtil = emailUtil;
        this.smsUtil = smsUtil;
    }

    /**
     * Listall users response entity.
     *
     * @return ResponseEntity which return list of user. and in else it just return not found status
     * @author Talha Farooq
     * @version 0.1
     * @description This function get and show all the user which are saved in database. The data from database comes in list.
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> listallUsers() {
        try {
            List<User> userList = userRepository.findAll();
            if (!userList.isEmpty()) {
                return ResponseEntity.ok().body(userList);
            } else
                return new ResponseEntity<>("List Empty", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot access List of User from database", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Save user response entity.
     *
     * @param newuser the newuser
     * @return responseEntity Status and user object
     * @author Talha Farooq
     * @version 0.1
     * @description Save user into database by getting values from controller and set date/time
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> saveUser(User newuser) {
        try {
            Calendar calendar = Calendar.getInstance();

            newuser.setCreatedDate(calendar.getTime());

            userRepository.save(newuser);
            return new ResponseEntity<>(newuser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update user response entity.
     *
     * @param user the user
     * @return only responseEntity Status and user
     * @author Talha Farooq
     * @version 0.1
     * @description update user into database by getting values from controller and set date/time
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> updateUser(User user) {
        try {
            Calendar date = Calendar.getInstance();
            user.setUpdatedDate(date.getTime());
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot update the user into database", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete user response entity.
     *
     * @param id the id
     * @return ResponseEntity response entity
     * @author Talha Farooq
     * @description Hide user in database
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> deleteUser(Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            user.get().setActive(false);
            userRepository.save(user.get());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot Access certain user id from database", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets userbyid.
     *
     * @param id the id
     * @return ResponseEntity with one object of user
     * @author Talha Farooq
     * @version 0.1
     * @description Find by ID user from database
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> getUserbyid(Long id) {
        try {
            User user = userRepository.findById(id).get();
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return new ResponseEntity<Object>("User does not Exist in database", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Custom API logic for email ;)
     *
     * @param email    the email
     * @param password the password
     * @return the boolean
     */
    public boolean findByEmailandPassword(String email, String password) {
        try {
            User user = userRepository.findByEmail(email);
            if (user.getPassword().equals(password)) {
                return true;
            } else
                return false;
        } catch (Exception e) {
            System.out.print("User not exist");
            return false;
        }
    }

    /**
     * Token send emailand msg response entity.
     *
     * @param userId the user id
     * @return the response entity
     * @Description It sends the token to message and email and set the verify status as false and save token in database
     * @author Talha Farooq
     * @since 14 October 2021
     */
    public ResponseEntity<Object> tokenSendEmailandMsg(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        try {
            if (user.isPresent()) {
                Random rnd = new Random(); // Generating a random number
                int token = rnd.nextInt(999999); // Generating a random number of 6 digits
                String message = "Please verify yourself \n Email token: " + token;

                SMS sms = new SMS();
                sms.setTo(user.get().getPhoneNumber());
                sms.setMessage(user.get().getFirstName() + " please verify yourself " + token);
                smsUtil.send(sms);
                emailUtil.sendMail(user.get().getEmail(), "Verification", message);
                user.get().setToken(token); // Set verify token as inactive

                Calendar date = Calendar.getInstance();
                long timeInSecs = date.getTimeInMillis();
                Date afterAdding10Mins = new Date(timeInSecs + (10 * 60 * 1000));
                user.get().setExpirationDate(afterAdding10Mins);

                userRepository.save(user.get());


                return new ResponseEntity<>("Mail and message Sent", HttpStatus.OK);
            } else
                return new ResponseEntity<>("User is not found", HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Verification sms and email response entity.
     *
     * @param emailToken the email token
     * @param email      the email
     * @return the response entity
     */
    public ResponseEntity<Object> verificationSmsAndEmail(int emailToken, String email) {
        try {
            User user = userRepository.findByEmailAndToken(email, emailToken);
            Calendar date = Calendar.getInstance();
            if (date.before(user.getExpirationDate())) {
                user.setAccountVerifyStatus(true);
                userRepository.save(user);
                return new ResponseEntity<>("YOU ARE NOW VERIFIED", HttpStatus.OK);
            } else return new ResponseEntity<>("Token Expired", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Recommended managers response entity.
     *
     * @param tag the tag
     * @return the response entity
     */
    public ResponseEntity<Object> recommendedManagers(String tag) {
        try {
            List<Object[]> userList = userRepository.findRecommendedManagers(tag);
            return maptoDTOclass(userList);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Recommended officers response entity.
     *
     * @param tag the tag
     * @return the response entity
     */
    public ResponseEntity<Object> recommendedOfficers(String tag) {
        try {
            List<Object[]> userList = userRepository.findRecommendedOfficers(tag);
            return maptoDTOclass(userList);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<Object> maptoDTOclass(List<Object[]> userList) {
        List<RecommendedManagerDTO> recommendedManagerDTOList = new ArrayList<>();
        for (Object[] dto : userList) {
            BigInteger id = (BigInteger) dto[0];
            String firstname = (String) dto[1];
            String lastName = (String) dto[2];
            String tags = (String) dto[3];
            long i = id.longValue();
            System.out.println("data is " + firstname + lastName + tags);

            recommendedManagerDTOList.add(new RecommendedManagerDTO(i, firstname, lastName, tags));
        }
        return new ResponseEntity<>(recommendedManagerDTOList, HttpStatus.OK);
    }

    /**
     * Selection manager officer response entity.
     *
     * @param managerId the manager id
     * @param officerId the officer id
     * @param projectId the project id
     * @return the response entity
     */
    public ResponseEntity<Object> selectionManagerOfficer(List<Long> managerId, List<Long> officerId, Long projectId) {
        try {
            Optional<Project> project = projectRepository.findById(projectId);
            if (project.isPresent()) {
                List<Project> projectList = new ArrayList<>();
                projectList.add(project.get());
                for (Long mId : managerId) {
                    Optional<User> user = userRepository.findById(mId);
                    user.get().setProjects(projectList);
                }
                for (Long oID : officerId) {
                    Optional<User> user = userRepository.findById(oID);
                    user.get().setProjects(projectList);
                }
            }
            return new ResponseEntity<>("Project updated with officers and managers", HttpStatus.OK);
        } catch (Exception e) {
            System.out.print(e);
            return new ResponseEntity<>("The user or project does not exist", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Rejection manager officer response entity.
     *
     * @param userId    the user id
     * @param projectId the project id
     * @return the response entity
     */
    public ResponseEntity<Object> rejectionManagerOfficer(Long userId, Long projectId) {
        try {
            Optional<Project> project = projectRepository.findById(projectId);
            if(project.isPresent()){
                userRepository.delete(userId);
                return new ResponseEntity<>("Done",HttpStatus.OK);
            }
            else return new ResponseEntity<>("The user or project does not exist", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.print(e);
            return new ResponseEntity<>("Cannot access database", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Export to pdf.
     *
     * @param response the response
     * @throws DocumentException the document exception
     * @throws IOException       the io exception
     */
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<User> listUsers = userRepository.findAll();
        UserPDFExporter exporter = new UserPDFExporter(listUsers);
        exporter.export(response);
    }
}
