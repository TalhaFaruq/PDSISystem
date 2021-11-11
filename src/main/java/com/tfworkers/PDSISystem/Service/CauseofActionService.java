package com.tfworkers.PDSISystem.Service;

import com.tfworkers.PDSISystem.Model.Entity.CauseofAction;
import com.tfworkers.PDSISystem.Repository.CauseofActionRepository;
import com.tfworkers.PDSISystem.Repository.NationalRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * The type Causeof action service.
 */
@Service
public class CauseofActionService {
    private CauseofActionRepository causeofActionRepository;

    /**
     * Instantiates a new Causeof action service.
     *
     * @param causeofActionRepository the causeof action repository
     */
    public CauseofActionService(CauseofActionRepository causeofActionRepository) {
        this.causeofActionRepository = causeofActionRepository;
    }

    private static final Logger logger = LogManager.getLogger(CauseofActionService.class);


    /**
     * List causeof action response entity.
     *
     * @return ResponseEntity which return list of cause of Actions. and in else it just return not found status
     * @author Talha Farooq
     * @version 0.1
     * @description This function get and show all the cause of Actions which are saved in database. The data from database comes in list.
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> listCauseofAction() {
        try {
            List<CauseofAction> causeofActions = causeofActionRepository.findByOrderByCreatedDateAsc();
            if (!causeofActions.isEmpty()) {
                logger.info("Getting list of Cause of Action");
                return ResponseEntity.ok().body(causeofActions);
            } else
                return new ResponseEntity<>("List Empty", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage(),"Error getting list of cause of action");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Save causeof actions response entity.
     *
     * @param causeofAction the causeof action
     * @return responseEntity Status and causeofAction object
     * @author Talha Farooq
     * @version 0.1
     * @description Save causeofAction into database by getting values from controller and set date/time
     * @creationDate 28 October 2021
     */
    public ResponseEntity<Object> saveCauseofActions(CauseofAction causeofAction) {
        try {
            Calendar date = Calendar.getInstance();
            causeofAction.setCreatedDate(date.getTime());
            causeofActionRepository.save(causeofAction);
            logger.info("Saving cause of action object");
            return new ResponseEntity<>(causeofAction, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),"Error in saving cause of action");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
