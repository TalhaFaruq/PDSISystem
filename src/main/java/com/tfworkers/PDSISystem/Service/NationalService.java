package com.tfworkers.PDSISystem.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.tfworkers.PDSISystem.Model.Entity.International;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tfworkers.PDSISystem.Model.Entity.National;
import com.tfworkers.PDSISystem.Repository.NationalRepository;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type National service.
 */
@Service
public class NationalService {

    private NationalRepository nationalRepository;

    /**
     * Constructor
     *
     * @param nationalRepository the national repository
     */
    public NationalService(NationalRepository nationalRepository) {
        this.nationalRepository = nationalRepository;
    }

    private static final Logger logger = LogManager.getLogger(NationalService.class);

    /**
     * List national response entity.
     *
     * @return ResponseEntity which return list of national. and in else it just         return not found status
     * @author Talha Farooq
     * @version 0.1
     * @description This function get and show all the national which are saved in              database. The data from database comes in list.
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> listNational() {
        try {
            List<National> nationalList = nationalRepository.findAllByIsActiveOrderByCreatedDate(true);
            if (!nationalList.isEmpty()) {
                logger.info("Showing National List");
                return ResponseEntity.ok().body(nationalList);
            } else
                return new ResponseEntity<Object>("List Empty", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Save national response entity.
     *
     * @param national the national
     * @return responseEntity Status and national object
     * @author Talha Farooq
     * @version 0.1
     * @description Save projects into database by getting values from controller              and set date/time
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> saveNational(National national) {
        try {
            Calendar date = Calendar.getInstance();
            national.setCreatedDate(date.getTime());
            nationalRepository.save(national);
            logger.info("Saving National Object");
            return new ResponseEntity<>(national, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update national response entity.
     *
     * @param national the national
     * @return only responseEntity Status and national
     * @author Talha Farooq
     * @version 0.1
     * @description update national into database by getting values from controller              and set date/time
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> updateNational(National national) {
        try {
            Calendar date = Calendar.getInstance();
            national.setUpdatedDate(date.getTime());
            nationalRepository.save(national);
            logger.info("Updating National Object");
            return new ResponseEntity<>(national, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete national response entity.
     *
     * @param id the id
     * @return ResponseEntity response entity
     * @author Talha Farooq
     * @description Hide national in database
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> deleteNational(Long id) {
        try {
            Optional<National> national = nationalRepository.findById(id);
            national.get().setActive(false);
            nationalRepository.save(national.get());
            logger.info("Deleted National");
            return new ResponseEntity<>(national, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets nationalbyid.
     *
     * @param id the id
     * @return ResponseEntity with one object of national
     * @author Talha Farooq
     * @version 0.1
     * @description Find by ID national from database
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> getNationalbyid(Long id) {
        try {
            Optional<National> national = nationalRepository.findById(id);
            logger.info("Getting by ID National");
            return ResponseEntity.ok().body(national.get());
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}