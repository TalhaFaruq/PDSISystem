package com.tfworkers.PDSISystem.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.Timeline;
import com.tfworkers.PDSISystem.Repository.TimelineRepository;

/**
 * The type Timeline service.
 */
@Service
public class TimelineService {

	private final TimelineRepository timelineRepository;

	/**
	 * Constructor
	 *
	 * @param timelineRepository the timeline repository
	 */
	public TimelineService(TimelineRepository timelineRepository) {
		this.timelineRepository = timelineRepository;
	}

	private static final Logger logger = LogManager.getLogger(UserService.class);

	/**
	 * List all timeline response entity.
	 *
	 * @return ResponseEntity which return list of timeline. and in else it just returns         not found status
	 * @author Talha Farooq
	 * @description This function get and show all the timelines which are saved in              database. The data from database comes in list.
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> listAllTimeline() {
		try {
			List<Timeline> timelineList = timelineRepository.findAll();
			if (!timelineList.isEmpty()) {
				logger.info("In Service class getting timeline list");
				return ResponseEntity.ok().body(timelineList);
			} else
				return new ResponseEntity<>("List Empty", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Error getting list");
			System.out.print(e);
			return new ResponseEntity<>("Cannot access List of timeline from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Save timeline response entity.
	 *
	 * @param timeline the timeline
	 * @return responseEntity Status and timeline object
	 * @author Talha Farooq
	 * @description Save timeline into database by getting values from controller and              set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> saveTimeline(Timeline timeline) {
		try {
			Calendar date = Calendar.getInstance();
			timeline.setCreatedDate(date.getTime());
			timelineRepository.save(timeline);
			logger.info("In Service class saving timeline");
			return new ResponseEntity<>(timeline, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error saving timeline");
			System.out.print(e);
			return new ResponseEntity<>("Cannot save values in database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Update timeline response entity.
	 *
	 * @param timeline the timeline
	 * @return only responseEntity Status and timeline
	 * @author Talha Farooq
	 * @description update timeline into database by getting values from controller and              set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> updateTimeline(Timeline timeline) {
		try {
			Calendar date = Calendar.getInstance();
			timeline.setUpdatedDate(date.getTime());
			timelineRepository.save(timeline);
			logger.info("Service class updating timeline");
			return new ResponseEntity<>(timeline, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cannot update the timeline into database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Delete timeline response entity.
	 *
	 * @param id the id
	 * @return ResponseEntity response entity
	 * @author Talha Farooq
	 * @description Hide timeline in database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> deleteTimeline(Long id) {
		try {
			Optional<Timeline> timeline = timelineRepository.findById(id);
			timeline.get().setActive(false);
			timelineRepository.save(timeline.get());
			logger.info("Delete timeline");
			return new ResponseEntity<>(timeline, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cannot Access certain timeline id from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Gets timeline id.
	 *
	 * @param id the id
	 * @return ResponseEntity with one object of user
	 * @author Talha Farooq
	 * @description Find by ID user from database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> getTimelineId(Long id) {
		try {
			Optional<Timeline> timeline = timelineRepository.findById(id);
			logger.info("Getting by ID timeline");
			return ResponseEntity.ok().body(timeline.get());
		} catch (Exception e) {
			return new ResponseEntity<>("tag timeline not Exist in database", HttpStatus.NOT_FOUND);
		}
	}



}
