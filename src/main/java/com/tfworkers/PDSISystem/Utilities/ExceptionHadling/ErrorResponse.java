package com.tfworkers.PDSISystem.Utilities.ExceptionHadling;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ErrorResponse {
    // customizing timestamp serialization format
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private int code;

    private HttpStatus status;

    private String message;

    private String path;



    public ErrorResponse(HttpStatus httpStatus, String message,String path) {
        timestamp = LocalDateTime.now();
        this.code = httpStatus.value();
        this.status = httpStatus;
        this.path = path;
        this.message = message;
    }


}