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

import com.tfworkers.PDSISystem.Model.Entity.International;
import com.tfworkers.PDSISystem.Repository.InternationalRepository;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type International service.
 */
@Service
public class InternationalService {
    private InternationalRepository internationalRepository;

    private static final Logger logger = LogManager.getLogger(InternationalService.class);


    /**
     * Constructor
     *
     * @param internationalRepository the international repository
     */
    public InternationalService(InternationalRepository internationalRepository) {
        this.internationalRepository = internationalRepository;
    }

    /**
     * List international response entity.s
     *
     * @return ResponseEntity which return list of international. and in else it just return not found status
     * @author Talha Farooq
     * @version 0.1
     * @description This function get and show all the international which are saved in database. The data from database comes in list.
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> listInternational() {
        try {
            List<International> internationalList = internationalRepository.findAllByIsActiveOrderByCreatedDate(true);
            if (!internationalList.isEmpty()) {
                logger.info("Showing list of International");
                return ResponseEntity.ok().body(internationalList);
            } else
                return new ResponseEntity<>("List Empty", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage(),"Error in getting list");
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Save international response entity.
     *
     * @param international the international
     * @return responseEntity Status and international object
     * @author Talha Farooq
     * @version 0.1
     * @description Save projects into database by getting values from controller              and set date/time
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> saveInternational(International international) {
        try{
            Calendar date = Calendar.getInstance();
            international.setCreatedDate(date.getTime());
            internationalRepository.save(international);
            logger.info("Saving International");
            return new ResponseEntity<>(international, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),"Error in saving international");
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update international response entity.
     *
     * @param international the international
     * @return only responseEntity Status and international
     * @author Talha Farooq
     * @version 0.1
     * @description update international into database by getting values from              controller and set date/time
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> updateInternational(International international) {
        try {
            Calendar date = Calendar.getInstance();
            international.setUpdatedDate(date.getTime());
            internationalRepository.save(international);
            logger.info("Updating International");
            return new ResponseEntity<>(international, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),"Error updating international");
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete international response entity.
     *
     * @param id the id
     * @return ResponseEntity response entity
     * @author Talha Farooq
     * @description Hide international in database
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> deleteInternational(Long id) {
        try {
            Optional<International> international = internationalRepository.findById(id);
            international.get().setActive(false);
            internationalRepository.save(international.get());
            logger.info("Deleting International");
            return new ResponseEntity<>(international, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),"Cannot delete International");
            System.out.println(e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets internationalbyid.
     *
     * @param id the id
     * @return ResponseEntity with one object of international
     * @author Talha Farooq
     * @version 0.1
     * @description Find by ID international from database
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> getInternationalbyid(Long id) {
        try {
            Optional<International> international = internationalRepository.findById(id);
            logger.info("Getting by Id international");
            return ResponseEntity.ok().body(international.get());
        } catch (Exception e) {
            logger.error(e.getMessage(),"Error in getting by id international");
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
