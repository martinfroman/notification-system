package com.martin.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException ex) {
        String errorMessage = String.format("Invalid value: %s provided for field: %s", ex.getValue(), ex.getPath().get(0).getFieldName());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = Optional.ofNullable(ex.getFieldError())
                .map(fieldError -> String.format("Field: '%s' - Problem: '%s'", fieldError.getField(), fieldError.getCode()))
                .orElse("Invalid arguments");
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}

