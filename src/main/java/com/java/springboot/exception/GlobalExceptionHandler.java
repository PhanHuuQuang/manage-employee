package com.java.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataExistException.class)
    public ResponseEntity<ErrorDetail> handleDataExistException(DataExistException e, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(new Date(),
                e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
    }

    public ResponseEntity<ErrorDetail> handleDataNotFoundException(DataNotFoundException e, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(new Date(),
                e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
}
