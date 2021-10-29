package com.tfworkers.PDSISystem.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.International;
import com.tfworkers.PDSISystem.Repository.InternationalRepository;

@Service
public class InternationalService {
	private InternationalRepository internationalRepository;

	/**
	 * Constructor
	 */
	public InternationalService(InternationalRepository internationalRepository) {
		this.internationalRepository = internationalRepository;
	}

	/**
	 * @return ResponseEntity which return list of international. and in else it
	 *         just return not found status
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This function get and show all the international which are saved
	 *              in database. The data from database comes in list.
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> listInternational() {
		try {
			List<International> internationalList = internationalRepository.findAll();
			if (!internationalList.isEmpty()) {
				return ResponseEntity.ok().body(internationalList);
			} else
				return new ResponseEntity<Object>("List Empty", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot access List of projects from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return responseEntity Status and international object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Save projects into database by getting values from controller
	 *              and set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> saveInternational(International international) {
		try {
			LocalDateTime date = LocalDateTime.now();
			international.setCreatedDate(date.toString());
			internationalRepository.save(international);
			return new ResponseEntity<Object>(international, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot save values in database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return only responseEntity Status and international
	 * @author Talha Farooq
	 * @version 0.1
	 * @description update international into database by getting values from
	 *              controller and set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> updateInternational(International international) {
		try {
			LocalDateTime date = LocalDateTime.now();
			international.setUpdatedDate(date.toString());
			internationalRepository.save(international);
			return new ResponseEntity<Object>(international, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot update the international into database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return ResponseEntity
	 * @author Talha Farooq
	 * @description Hide international in database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> deleteInternational(Long id) {
		try {
			Optional<International> international = internationalRepository.findById(id);
			international.get().setActive(false);
			internationalRepository.save(international.get());
			return new ResponseEntity<Object>(international, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot Access certain project id from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return ResponseEntity with one object of international
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Find by ID international from database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> getInternationalbyid(Long id) {
		try {
			Optional<International> international = internationalRepository.findById(id);
			return ResponseEntity.ok().body(international.get());
		} catch (Exception e) {
			return new ResponseEntity<Object>("International does not Exist in database", HttpStatus.NOT_FOUND);
		}
	}

}
