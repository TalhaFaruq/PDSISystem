package com.tfworkers.PDSISystem.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.National;
import com.tfworkers.PDSISystem.Repository.NationalRepository;

@Service
public class NationalService {

	private NationalRepository nationalRepository;

	/**
	 * Constructor
	 */
	public NationalService(NationalRepository nationalRepository) {
		this.nationalRepository = nationalRepository;
	}

	/**
	 * @return ResponseEntity which return list of national. and in else it just
	 *         return not found status
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This function get and show all the national which are saved in
	 *              database. The data from database comes in list.
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> listNational() {
		try {
			List<National> nationalList = nationalRepository.findAll();
			if (!nationalList.isEmpty()) {
				return ResponseEntity.ok().body(nationalList);
			} else
				return new ResponseEntity<Object>("List Empty", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot access List of projects from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return responseEntity Status and national object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Save projects into database by getting values from controller
	 *              and set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> saveNational(National national) {
		try {
			Calendar date = Calendar.getInstance();
			national.setCreatedDate(date.getTime());
			nationalRepository.save(national);
			return new ResponseEntity<Object>(national, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot save values in database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return only responseEntity Status and national
	 * @author Talha Farooq
	 * @version 0.1
	 * @description update national into database by getting values from controller
	 *              and set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> updateNational(National national) {
		try {
			Calendar date = Calendar.getInstance();
			national.setUpdatedDate(date.getTime());
			nationalRepository.save(national);
			return new ResponseEntity<Object>(national, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot update the national into database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return ResponseEntity
	 * @author Talha Farooq
	 * @description Hide national in database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> deleteNational(Long id) {
		try {
			Optional<National> national = nationalRepository.findById(id);
			national.get().setActive(false);
			nationalRepository.save(national.get());
			return new ResponseEntity<Object>(national, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot Access certain project id from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return ResponseEntity with one object of national
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Find by ID national from database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> getNationalbyid(Long id) {
		try {
			Optional<National> national = nationalRepository.findById(id);
			return ResponseEntity.ok().body(national.get());
		} catch (Exception e) {
			return new ResponseEntity<Object>("National does not Exist in database", HttpStatus.NOT_FOUND);
		}
	}

}