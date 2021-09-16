package com.airfrance.technicaltest.controller;

import com.airfrance.technicaltest.dto.ErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the global exception handler.
 *
 * @author r-fonkoue
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    @Autowired
    MessageSource messageSource;

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    ResponseEntity<ErrorResponse> exceptionHandler(ValidationException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ErrorResponse> exceptionHandler(ConstraintViolationException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentNotValidException e) {
        List<String> errorMessages = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            errorMessages.add(error.getDefaultMessage());
        });
        return new ResponseEntity<>(new ErrorResponse(String.join(" | ", errorMessages), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(DateTimeParseException.class)
    ResponseEntity<ErrorResponse> exceptionHandler(DateTimeParseException e) {
        return new ResponseEntity<>(new ErrorResponse(
                String.format(messageSource.getMessage("error.date.not.be.parsed", null, LocaleContextHolder.getLocale()), e.getParsedString()), HttpStatus.BAD_REQUEST)
                , HttpStatus.BAD_REQUEST);

    }

    @ResponseBody
    @ExceptionHandler(InvalidFormatException.class)
    ResponseEntity<ErrorResponse> exceptionHandler(InvalidFormatException e) {
        if (e.getTargetType().isEnum()) {
            return new ResponseEntity<>(new ErrorResponse(
                    String.format(messageSource.getMessage("error.invalid.format.enum", null, LocaleContextHolder.getLocale()), e.getValue(), Arrays.toString(e.getTargetType().getEnumConstants())), HttpStatus.BAD_REQUEST)
                    , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ErrorResponse(
                String.format(messageSource.getMessage("error.invalid.format", null, LocaleContextHolder.getLocale()), e.getValue(), e.getTargetType().getSimpleName()), HttpStatus.BAD_REQUEST)
                , HttpStatus.BAD_REQUEST);

    }

    @ResponseBody
    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<ErrorResponse> exceptionHandler(ResponseStatusException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getReason(), e.getStatus()), e.getStatus());
    }

}