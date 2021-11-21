package com.tfworkers.PDSISystem.Controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;
import com.tfworkers.PDSISystem.Model.DTO.JwtRequest;
import com.tfworkers.PDSISystem.Model.DTO.SelectUsersProjectDTO;

import com.tfworkers.PDSISystem.Security.JwtResponse;
import com.tfworkers.PDSISystem.Security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.tfworkers.PDSISystem.Model.Entity.User;
import com.tfworkers.PDSISystem.Service.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

/**
 * The type User controller.
 */
@EnableSwagger2
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    private final String na = "Not Authorize";

    /**
     * UserContorller constructor
     *
     * @param userService the user service
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This is token for checking authorization
     */
    final private String key = "12345";

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
     * Login it takes email and password from front end then check from database by
     * calling object with email
     *
     * @return the response entity
     */
    @PutMapping("/login")
    public ResponseEntity<Object> login(@RequestHeader JwtRequest jwtRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    /**
     * List all users response entity.
     *
     * @param token the token
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @createdTime 28 October 2021
     */
    @GetMapping("/all")
    public ResponseEntity<Object> listAllUsers(@RequestHeader("Authorization") String token) {
        return userService.listAllUsers();
    }

    /**
     * Gets by user id.
     *
     * @param token the token
     * @param id    the id
     * @return ResponseEntity with object
     * @author Talha Farooq
     * @version 0.1
     * @description This API get the user from database in object and shows it in              front end. With Authorization token.
     * @createdTime 28 October 2021
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getByUserID(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        if (authorization(token)) {
            return userService.getUserById(id);
        } else
            return new ResponseEntity<>(na, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Add user response entity.
     *
     * @param token the token
     * @param user  the user
     * @return Just returns ResponseEntity
     * @author Talha Farooq
     * @version 0.1
     * @description This API get the user from front end in json. With Authorization              token.
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
     * Update user response entity.
     *
     * @param token the token
     * @param user  the user
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
     * Delete user response entity.
     *
     * @param token the token
     * @param id    the id
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
     * Email response entity.
     *
     * @param token  the token
     * @param userId the user id
     * @return Just returns ResponseEntity
     * @throws MessagingException the messaging exception
     * @author Talha Farooq
     * @version 0.3
     * @description This API create message and email with token
     * @createdTime 5 October 2021
     */
    @PutMapping("/email/{userId}")
    public ResponseEntity<Object> email(@RequestHeader("Authorization") String token,
                                        @PathVariable(value = "userId") Long userId) throws MessagingException {
        if (authorization(token)) {
            return userService.tokenSendEmailandMsg(userId);
        } else
            return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Verification response entity.
     *
     * @param token      the token
     * @param emailToken the email token
     * @param email      the email
     * @return the response entity
     */
    @PutMapping("/verify/{userId}")
    public ResponseEntity<Object> verification(@RequestHeader("Authorization") String token,
                                               @RequestHeader("emailToken") int emailToken,
                                               @RequestHeader("email") String email) {
        if (authorization(token)) {
            return userService.verificationSmsAndEmail(emailToken, email);
        } else return new ResponseEntity(na, HttpStatus.UNAUTHORIZED);
    }


    /**
     * Show recommended managers response entity.
     *
     * @param tag the tag
     * @return the response entity
     */
    @GetMapping("/recommendedManagers")
    public ResponseEntity<Object> showRecommendedManagers(@RequestHeader("tag") String tag) {
        return userService.recommendedManagers(tag);
    }

    /**
     * Show recommended officers response entity.
     *
     * @param tag the tag
     * @return the response entity
     */
    @GetMapping("/recommendedOfficers")
    public ResponseEntity<Object> showRecommendedOfficers(@RequestHeader("tag") String tag) {
        return userService.recommendedOfficers(tag);
    }

    /**
     * Select officers managers response entity.
     *
     * @param token                 the token
     * @param selectUsersProjectDTO the select managers dto
     * @return the response entity
     */
    @PutMapping("/selectManagerOfficers")
    public ResponseEntity<Object> selectOfficersManagers(@RequestHeader("Authorization") String token, @RequestBody SelectUsersProjectDTO selectUsersProjectDTO) {
        return userService.selectionManagerOfficer(selectUsersProjectDTO.getUser(), selectUsersProjectDTO.getProjectId(), selectUsersProjectDTO.getPost());
    }

    /**
     * Reject manager officers response entity.
     *
     * @param token     the token
     * @param userId    the user id
     * @param projectId the project id
     * @return the response entity
     */
    @DeleteMapping("/RejectManagersOfficers")
    public ResponseEntity<Object> rejectManagerOfficers(@RequestHeader("Authorization") String token, @RequestHeader("userId") Long userId, @RequestHeader("projectId") Long projectId) {
        return userService.rejectionManagerOfficer(userId, projectId);
    }

    /**
     * Export to pdf.
     *
     * @param response the response
     * @throws DocumentException the document exception
     * @throws IOException       the io exception
     */
    @GetMapping("/exporttoPDF")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        userService.userExportToPDF(response);
    }

}
