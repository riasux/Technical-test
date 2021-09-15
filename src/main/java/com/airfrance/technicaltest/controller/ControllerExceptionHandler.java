package com.airfrance.technicaltest.controller;

import com.airfrance.technicaltest.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * Represents the global exception handler.
 *
 * @author r-fonkoue
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    ResponseEntity<ErrorResponse> exceptionHandler(ValidationException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ErrorResponse> exceptionHandler(ConstraintViolationException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentNotValidException e) {
        StringBuilder errorMessage = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            errorMessage.append(" | "+error.getDefaultMessage());
        });
        return new ResponseEntity<>(new ErrorResponse(errorMessage.toString()), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<ErrorResponse> exceptionHandler(ResponseStatusException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), e.getStatus());
    }
}