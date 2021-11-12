package com.tfworkers.PDSISystem.Utilities.ExceptionHadling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.InputMismatchException;
import java.util.Objects;

@ControllerAdvice
public class ExceptionHandling {
    private static final Logger log = LogManager.getLogger(ExceptionHandling.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static ResponseEntity<Object> handleException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String errorResult = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorDefaultMessage = error.getDefaultMessage();
            log.info("field is " + fieldName + " " + errorDefaultMessage);
        });
        log.info("an error has occured ....." + e.getClass() + errorResult);
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, errorResult, request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RuntimeException.class, MissingRequestValueException.class, InputMismatchException.class})
    public ResponseEntity<ErrorResponse> inputValidationException(Exception e, HttpServletRequest request) {
        log.info("some error has occurred see logs for more details ....general exception is \n " + e.toString() + request.getRequestURI());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

}
