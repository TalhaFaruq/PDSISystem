package com.tfworkers.PDSISystem.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.FuturePlan;
import com.tfworkers.PDSISystem.Repository.FuturePlanRepository;

@Service
public class FuturePlanService {
	private FuturePlanRepository futurePlanRepository;

	/**
	 * Constructor
	 */
	public FuturePlanService(FuturePlanRepository futurePlanRepository) {
		this.futurePlanRepository = futurePlanRepository;
	}

	/**
	 * @return ResponseEntity which return list of futurePlanes. and in else it just
	 *         return not found status
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This function get and show all the futurePlan which are saved in
	 *              database. The data from database comes in list.
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> listFuturePlan() {
		try {
			List<FuturePlan> futurePlanList = futurePlanRepository.findAll();
			if (!futurePlanList.isEmpty()) {
				return ResponseEntity.ok().body(futurePlanList);
			} else
				return new ResponseEntity<Object>("List Empty", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot access List of projects from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return responseEntity Status and futurePlan object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Save projects into database by getting values from controller
	 *              and set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> saveFuturePlan(FuturePlan futurePlan) {
		try {
			Calendar date = Calendar.getInstance();
			futurePlan.setCreatedDate(date.getTime());
			futurePlanRepository.save(futurePlan);
			return new ResponseEntity<Object>(futurePlan, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot save values in database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return only responseEntity Status and futurePlan
	 * @author Talha Farooq
	 * @version 0.1
	 * @description update futurePlan into database by getting values from
	 *              controller and set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> updateFuturePlan(FuturePlan futurePlan) {
		try {
			Calendar date = Calendar.getInstance();
			futurePlan.setUpdatedDate(date.getTime());
			futurePlanRepository.save(futurePlan);
			return new ResponseEntity<Object>(futurePlan, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot update the futurePlan into database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return ResponseEntity
	 * @author Talha Farooq
	 * @description Hide futurePlan in database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> deleteFuturePlan(Long id) {
		try {
			Optional<FuturePlan> futurePlan = futurePlanRepository.findById(id);
			futurePlan.get().setActive(false);
			futurePlanRepository.save(futurePlan.get());
			return new ResponseEntity<Object>(futurePlan, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot Access certain project id from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return ResponseEntity with one object of futurePlan
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Find by ID futurePlan from database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> getFuturePlanbyid(Long id) {
		try {
			Optional<FuturePlan> futurePlan = futurePlanRepository.findById(id);
			return ResponseEntity.ok().body(futurePlan.get());
		} catch (Exception e) {
			return new ResponseEntity<Object>("FuturePlan does not Exist in database", HttpStatus.NOT_FOUND);
		}
	}

}
