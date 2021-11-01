package com.tfworkers.PDSISystem.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.Process;
import com.tfworkers.PDSISystem.Repository.ProcessRepository;

@Service
public class ProcessService {

	private ProcessRepository processRepository;

	/**
	 * Constructor
	 */
	public ProcessService(ProcessRepository processRepository) {
		this.processRepository = processRepository;
	}

	/**
	 * @return ResponseEntity which return list of processes. and in else it just
	 *         return not found status
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This function get and show all the process which are saved in
	 *              database. The data from database comes in list.
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> listProcess() {
		try {
			List<Process> processList = processRepository.findAll();
			if (!processList.isEmpty()) {
				return ResponseEntity.ok().body(processList);
			} else
				return new ResponseEntity<Object>("List Empty", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot access List of projects from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return responseEntity Status and process object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Save projects into database by getting values from controller
	 *              and set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> saveProcess(Process process) {
		try {
			Calendar date = Calendar.getInstance();
			process.setCreatedDate(date.getTime());
			processRepository.save(process);
			return new ResponseEntity<Object>(process, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot save values in database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return only responseEntity Status and process
	 * @author Talha Farooq
	 * @version 0.1
	 * @description update process into database by getting values from controller
	 *              and set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> updateProcess(Process process) {
		try {
			Calendar date = Calendar.getInstance();
			process.setUpdatedDate(date.getTime());
			processRepository.save(process);
			return new ResponseEntity<Object>(process, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot update the process into database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return ResponseEntity
	 * @author Talha Farooq
	 * @description Hide process in database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> deleteProcess(Long id) {
		try {
			Optional<Process> process = processRepository.findById(id);
			process.get().setActive(false);
			processRepository.save(process.get());
			return new ResponseEntity<Object>(process, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot Access certain project id from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return ResponseEntity with one object of process
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Find by ID process from database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> getProcessbyid(Long id) {
		try {
			Optional<Process> process = processRepository.findById(id);
			return ResponseEntity.ok().body(process.get());
		} catch (Exception e) {
			return new ResponseEntity<Object>("Process does not Exist in database", HttpStatus.NOT_FOUND);
		}
	}

}
