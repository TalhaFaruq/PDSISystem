package com.tfworkers.PDSISystem.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;

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
	 *         not found status
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This function get and show all the user which are saved in
	 *              database. The data from database comes in list.
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
	 *              set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> saveUser(User newuser) {
		try {
			LocalDateTime date = LocalDateTime.now();

			newuser.setCreatedDate(date.toString());

			userRepository.save(newuser);
			return new ResponseEntity<Object>(newuser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot save values in database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return only responseEntity Status and user
	 * @author Talha Farooq
	 * @version 0.1
	 * @description update user into database by getting values from controller and
	 *              set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> updateUser(User user) {
		try {
			LocalDateTime date = LocalDateTime.now();
			user.setUpdatedDate(date.toString());
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
	 * @Description It sends the token to message and email and set the verify
	 *              status as false and save token in database
	 *
	 * @param userId the user id
	 * @return the response entity
	 * @throws MessagingException the messaging exception
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

				return new ResponseEntity<>("Mail and message Sent", HttpStatus.OK);
			} else
				return new ResponseEntity<>("User is not found", HttpStatus.NOT_FOUND);
		} catch (Exception exception) {
			return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Object> verificationsmsandemail(int smstoken, int emailtoken, String email) {
		try {
			if (emailtoken == smstoken) { // Taking email and message token and saving them differently will waste storage
				// instead we check them in this if statement
				User user = userRepository.findByEmailAndToken(email, emailtoken); // If the user present then user will
																					// get values otherwise user will be
																					// empty
				user.setAccountVerifyStatus(true);
				userRepository.save(user);
				return new ResponseEntity<Object>(user, HttpStatus.OK);
			} else
				return new ResponseEntity<Object>("Invalid Token", HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.UNAUTHORIZED);
		}
	}
}
