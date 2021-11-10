package com.tfworkers.PDSISystem.Service;

import com.tfworkers.PDSISystem.Model.Entity.CauseofAction;
import com.tfworkers.PDSISystem.Repository.CauseofActionRepository;
import com.tfworkers.PDSISystem.Repository.NationalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
            List<CauseofAction> causeofActions = causeofActionRepository.findAll();
            if (!causeofActions.isEmpty()) {
                return ResponseEntity.ok().body(causeofActions);
            } else
                return new ResponseEntity<Object>("List Empty", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<Object>("Cannot access List of causeofActions from database", HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<Object>(causeofAction, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>("Cannot save values in database", HttpStatus.NOT_FOUND);
        }
    }




}
