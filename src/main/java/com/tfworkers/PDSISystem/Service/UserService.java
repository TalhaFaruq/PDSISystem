package com.tfworkers.PDSISystem.Service;

import java.math.BigInteger;
import java.util.*;

import javax.mail.MessagingException;


import com.tfworkers.PDSISystem.Model.Entity.DTO.RecommendedManagerDTO;
//import com.tfworkers.PDSISystem.Repository.RepositoryDTO.RecommendedManagerRepository;
//import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.SMS;
import com.tfworkers.PDSISystem.Model.Entity.User;
import com.tfworkers.PDSISystem.Repository.UserRepository;
import com.tfworkers.PDSISystem.Utilities.EmailUtil;
import com.tfworkers.PDSISystem.Utilities.SmsUtil;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailUtil emailUtil;
    private final SmsUtil smsUtil;


    /**
     * Constructor
     */
    public UserService(UserRepository userRepository, EmailUtil emailUtil, SmsUtil smsUtil) {
        this.userRepository = userRepository;
        this.emailUtil = emailUtil;
        this.smsUtil = smsUtil;
    }

    /**
     * @return ResponseEntity which return list of user. and in else it just return
     * not found status
     * @author Talha Farooq
     * @version 0.1
     * @description This function get and show all the user which are saved in
     * database. The data from database comes in list.
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> listallUsers() {
        try {
            List<User> userList = userRepository.findAll();
            if (!userList.isEmpty()) {
                return ResponseEntity.ok().body(userList);
            } else
                return new ResponseEntity<Object>("List Empty", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<Object>("Cannot access List of User from database", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return responseEntity Status and user object
     * @author Talha Farooq
     * @version 0.1
     * @description Save user into database by getting values from controller and
     * set date/time
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
     * @return only responseEntity Status and user
     * @author Talha Farooq
     * @version 0.1
     * @description update user into database by getting values from controller and
     * set date/time
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> updateUser(User user) {
        try {
            Calendar date = Calendar.getInstance();
            user.setUpdatedDate(date.getTime());
            userRepository.save(user);
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>("Cannot update the user into database", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return ResponseEntity
     * @author Talha Farooq
     * @description Hide user in database
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> deleteUser(Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            user.get().setActive(false);
            userRepository.save(user.get());
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>("Cannot Access certain user id from database", HttpStatus.NOT_FOUND);
        }
    }

    /**
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
     * @param userId the user id
     * @return the response entity
     * @throws MessagingException the messaging exception
     * @Description It sends the token to message and email and set the verify
     * status as false and save token in database
     * @author Talha Farooq
     * @since 14 October 2021
     */
    public ResponseEntity<Object> tokensendemailandsms(Long userId) throws MessagingException {
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

    public ResponseEntity<Object> recommendedManagers(String tag) {
        try {
            List<Object[]> userList = userRepository.findAllByTagsContaining(tag);
            List<RecommendedManagerDTO> recommendedManagerDTOList = new ArrayList<>();
            for (Object[] dto : userList) {


                BigInteger id = (BigInteger) dto[0];
                String firstname = (String) dto[1];
                String lastName = (String) dto[2];
                String tags = (String) dto[3];

                long i = id.longValue();

                System.out.println("data is " +firstname+lastName+tags);

                recommendedManagerDTOList.add(new RecommendedManagerDTO( i,firstname, lastName, tags));


            }
            return new ResponseEntity<>(recommendedManagerDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

}
