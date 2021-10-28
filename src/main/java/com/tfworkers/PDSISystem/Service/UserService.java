package com.tfworkers.PDSISystem.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.User;
import com.tfworkers.PDSISystem.Repository.UserRepository;


@Service
public class UserService {

	private final UserRepository userRepository;

	
	/**
	 * Constructor
	 */
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
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
			return new ResponseEntity<Object>(user,HttpStatus.OK);
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
            } else return false;
        } catch (Exception e) {
            System.out.print("User not exist");
            return false;
        }
    }
}
