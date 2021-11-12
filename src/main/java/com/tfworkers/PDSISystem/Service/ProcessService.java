package com.tfworkers.PDSISystem.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.Process;
import com.tfworkers.PDSISystem.Repository.ProcessRepository;

/**
 * The type Process service.
 */
@Service
public class ProcessService {

	private ProcessRepository processRepository;

	/**
	 * Constructor
	 *
	 * @param processRepository the process repository
	 */
	public ProcessService(ProcessRepository processRepository) {
		this.processRepository = processRepository;
	}

	private static final Logger logger = LogManager.getLogger(ProcessService.class);

	/**
	 * List process response entity.
	 *
	 * @return ResponseEntity which return list of processes. and in else it just         return not found status
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This function get and show all the process which are saved in              database. The data from database comes in list.
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> listProcess() {
		try {
			List<Process> processList = processRepository.findAllByIsActiveOrderByCreatedDate(true);
			if (!processList.isEmpty()) {
				logger.info("Getting list of Process");
				return ResponseEntity.ok().body(processList);
			} else
				return new ResponseEntity<>("List Empty", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Save process response entity.
	 *
	 * @param process the process
	 * @return responseEntity Status and process object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Save projects into database by getting values from controller              and set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> saveProcess(Process process) {
		try {
			Calendar date = Calendar.getInstance();
			process.setCreatedDate(date.getTime());
			processRepository.save(process);
			return new ResponseEntity<>(process, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Update process response entity.
	 *
	 * @param process the process
	 * @return only responseEntity Status and process
	 * @author Talha Farooq
	 * @version 0.1
	 * @description update process into database by getting values from controller              and set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> updateProcess(Process process) {
		try {
			Calendar date = Calendar.getInstance();
			process.setUpdatedDate(date.getTime());
			processRepository.save(process);
			logger.info("Updating Process");
			return new ResponseEntity<>(process, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Delete process response entity.
	 *
	 * @param id the id
	 * @return ResponseEntity response entity
	 * @author Talha Farooq
	 * @description Hide process in database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> deleteProcess(Long id) {
		try {
			Optional<Process> process = processRepository.findById(id);
			process.get().setActive(false);
			processRepository.save(process.get());
			logger.info("Deleting Process",process);
			return new ResponseEntity<>(process, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Gets processbyid.
	 *
	 * @param id the id
	 * @return ResponseEntity with one object of process
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Find by ID process from database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> getProcessbyid(Long id) {
		try {
			Optional<Process> process = processRepository.findById(id);
			logger.info("Getting Process by ID",process);
			return ResponseEntity.ok().body(process.get());
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
