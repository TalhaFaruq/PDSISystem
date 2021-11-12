package com.tfworkers.PDSISystem.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.FuturePlan;
import com.tfworkers.PDSISystem.Repository.FuturePlanRepository;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Future plan service.
 */
@Service
public class FuturePlanService {
	private FuturePlanRepository futurePlanRepository;

	private static final Logger logger = LogManager.getLogger(FuturePlanService.class);

	/**
	 * Constructor
	 *
	 * @param futurePlanRepository the future plan repository
	 */
	public FuturePlanService(FuturePlanRepository futurePlanRepository) {
		this.futurePlanRepository = futurePlanRepository;
	}

	/**
	 * List future plan response entity.
	 *
	 * @return ResponseEntity which return list of futurePlanes. and in else it just  return not found status
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This function get and show all the futurePlan which are saved in database. The data from database comes in list.
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> listFuturePlan() {
		try {
			List<FuturePlan> futurePlanList = futurePlanRepository.findAllByIsActiveOrderByCreatedDate(true);
			if (!futurePlanList.isEmpty()) {
				logger.info("Getting list of Future Plans");
				return ResponseEntity.ok().body(futurePlanList);
			} else
				return new ResponseEntity<>("List Empty", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error(e.getMessage(),"Error getting list of future plans");
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Save future plan response entity.
	 *
	 * @param futurePlan the future plan
	 * @return responseEntity Status and futurePlan object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Save projects into database by getting values from controller and set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> saveFuturePlan(FuturePlan futurePlan) {
		try {
			Calendar date = Calendar.getInstance();
			futurePlan.setCreatedDate(date.getTime());
			futurePlanRepository.save(futurePlan);
			logger.info("Saving future plans object");
			return new ResponseEntity<>(futurePlan, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(),"Error saving future plan");
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Update future plan response entity.
	 *
	 * @param futurePlan the future plan
	 * @return only responseEntity Status and futurePlan
	 * @author Talha Farooq
	 * @version 0.1
	 * @description update futurePlan into database by getting values from controller and set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> updateFuturePlan(FuturePlan futurePlan) {
		try {
			Calendar date = Calendar.getInstance();
			futurePlan.setUpdatedDate(date.getTime());
			futurePlanRepository.save(futurePlan);
			logger.info("Updating future plans");
			return new ResponseEntity<>(futurePlan, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(),"Error updating future plans");
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Delete future plan response entity.
	 *
	 * @param id the id
	 * @return ResponseEntity response entity
	 * @author Talha Farooq
	 * @description Hide futurePlan in database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> deleteFuturePlan(Long id) {
		try {
			Optional<FuturePlan> futurePlan = futurePlanRepository.findById(id);
			futurePlan.get().setActive(false);
			futurePlanRepository.save(futurePlan.get());
			logger.info("Deleting Future Plan");
			return new ResponseEntity<>(futurePlan, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(),"Error in deleting future plan");
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Gets future planbyid.
	 *
	 * @param id the id
	 * @return ResponseEntity with one object of futurePlan
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Find by ID futurePlan from database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> getFuturePlanbyid(Long id) {
		try {
			Optional<FuturePlan> futurePlan = futurePlanRepository.findById(id);
			logger.info("getting future plan by ID");
			return ResponseEntity.ok().body(futurePlan.get());
		} catch (Exception e) {
			logger.error(e.getMessage(),"Error getting by ID");
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
