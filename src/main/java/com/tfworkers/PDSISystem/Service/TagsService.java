package com.tfworkers.PDSISystem.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.Tags;
import com.tfworkers.PDSISystem.Repository.TagsRepository;

@Service
public class TagsService {

	private TagsRepository tagsRepository;

	/**
	 * Constructor
	 */
	public TagsService(TagsRepository tagsRepository) {
		this.tagsRepository = tagsRepository;
	}

	/**
	 * @return ResponseEntity which return list of tags. and in else it just return
	 *         not found status
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This function get and show all the tags which are saved in
	 *              database. The data from database comes in list.
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> listTags() {
		try {
			List<Tags> tagsList = tagsRepository.findAll();
			if (!tagsList.isEmpty()) {
				return ResponseEntity.ok().body(tagsList);
			} else
				return new ResponseEntity<Object>("List Empty", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot access List of tags from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return responseEntity Status and tags object
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Save tags into database by getting values from controller and
	 *              set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> saveTags(Tags tag) {
		try {
			Calendar date = Calendar.getInstance();
			tag.setCreatedDate(date.getTime());
			tagsRepository.save(tag);
			return new ResponseEntity<Object>(tag, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot save values in database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return only responseEntity Status and tags
	 * @author Talha Farooq
	 * @version 0.1
	 * @description update tags into database by getting values from controller and
	 *              set date/time
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> updateTags(Tags tag) {
		try {
			Calendar date = Calendar.getInstance();
			tag.setUpdatedDate(date.getTime());
			tagsRepository.save(tag);
			return new ResponseEntity<Object>(tag, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot update the tags into database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return ResponseEntity
	 * @author Talha Farooq
	 * @description Hide tags in database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> deleteTags(Long id) {
		try {
			Optional<Tags> tag = tagsRepository.findById(id);
			tag.get().setActive(false);
			tagsRepository.save(tag.get());
			return new ResponseEntity<Object>(tag, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Cannot Access certain tags id from database", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @return ResponseEntity with one object of tag
	 * @author Talha Farooq
	 * @version 0.1
	 * @description Find by ID tag from database
	 * @creationDate 28 October 2021
	 */
	public ResponseEntity<Object> getTagbyid(Long id) {
		try {
			Optional<Tags> tag = tagsRepository.findById(id);
			return ResponseEntity.ok().body(tag.get());
		} catch (Exception e) {
			return new ResponseEntity<Object>("tags does not Exist in database", HttpStatus.NOT_FOUND);
		}
	}

}
