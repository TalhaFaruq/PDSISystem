package com.tfworkers.PDSISystem.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Timeline;
import com.tfworkers.PDSISystem.Repository.TimelineRepository;

@Service
public class TimelineService {

	private final TimelineRepository timelineRepository;

	/**
	 * Constructor
	 */
	public TimelineService(TimelineRepository timelineRepository) {
		this.timelineRepository = timelineRepository;
	}

	/**
	 * @return ResponseEntity which return list of time line. and in else it just return
	 *         not found status
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This function get and show all the time lines which are saved in
	 *              database. The data from database comes in list.
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> listAllTimeline() {
		try {
			List<Timeline> timelineList = timelineRepository.findAll();
			if (!timelineList.isEmpty()) {
				return ResponseEntity.ok().body(timelineList);
			} else
				return new ResponseEntity<Object>("List Empty", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot access List of User from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return responseEntity Status and time line object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Save time line into database by getting values from controller and
	 *              set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> saveTimeline(Timeline timeline) {
		try {
			LocalDateTime date = LocalDateTime.now();
			timeline.setCreatedDate(date.toString());
			timelineRepository.save(timeline);
			return new ResponseEntity<Object>(timeline, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot save values in database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return only responseEntity Status and time line
	 * @author Talha Farooq
	 * @version 0.1
	 * @description update time line into database by getting values from controller and
	 *              set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> updateTimeline(Timeline timeline) {
		try {
			LocalDateTime date = LocalDateTime.now();
			timeline.setUpdatedDate(date.toString());
			timelineRepository.save(timeline);
			return new ResponseEntity<Object>(timeline, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot update the user into database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return ResponseEntity
	 * @author Talha Farooq
	 * @description Hide time line in database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> deleteTimeline(Long id) {
		try {
			Optional<Timeline> timeline = timelineRepository.findById(id);
			timeline.get().setActive(false);
			timelineRepository.save(timeline.get());
			return new ResponseEntity<Object>(timeline, HttpStatus.OK);
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
			Optional<Timeline> timeline = timelineRepository.findById(id);
			return ResponseEntity.ok().body(timeline.get());
		} catch (Exception e) {
			return new ResponseEntity<Object>("User does not Exist in database", HttpStatus.NOT_FOUND);
		}

	}

}
