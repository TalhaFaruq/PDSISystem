package com.tfworkers.PDSISystem.Controller;

import com.tfworkers.PDSISystem.Model.Entity.CauseofAction;
import com.tfworkers.PDSISystem.Service.CauseofActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Cause of action controller.
 */
@EnableSwagger2
@RestController
@RequestMapping("/causeofController")
public class CauseofActionController {
    private final CauseofActionService causeofActionService;
    private final String na = "Not Authorize";


    /**
     * Instantiates a new Causeof action controller.
     *
     * @param causeofActionService the causeof action service
     */
    public CauseofActionController(CauseofActionService causeofActionService) {
        this.causeofActionService = causeofActionService;
    }

    private String key = "12345";

    /**
     * Authorization function
     *
     * @param token the token
     * @return the boolean
     */
    public Boolean authorization(String token) {
        return key.equals(token);
    }

    /**
     * List all causeof action response entity.
     *
     * @param token the token
     * @return the response entity
     */
    @GetMapping("all")
    public ResponseEntity<Object> listAllCauseofAction(@RequestHeader("Authorization") String token) {

        if (authorization(token)) {
            return causeofActionService.listCauseofAction();
        } else
            return new ResponseEntity<Object>(na, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Add process response entity.
     *
     * @param token         the token
     * @param causeofAction the causeof action
     * @return the response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addProcess(@RequestHeader("Authorization") String token, @RequestBody CauseofAction causeofAction) {
        if (authorization(token)) {
            return causeofActionService.saveCauseofActions(causeofAction);
        } else
            return new ResponseEntity<Object>(na, HttpStatus.OK);
    }

    /**
     * Input validation exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<Object> inputValidationException(Exception e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }
}
