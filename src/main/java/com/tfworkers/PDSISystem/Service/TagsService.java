package com.tfworkers.PDSISystem.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Tags;

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
	 * @return ResponseEntity which return list of time line. and in else it just return
	 *         not found status
	 * @author Talha Farooq
	 * @version 0.1
	 * @description This function get and show all the time lines which are saved in
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
			return new ResponseEntity<Object>("Cannot access List of User from database", HttpStatus.NOT_FOUND);
		}
	}
	

}
