package com.eliel.inotes.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        return new ResponseEntity<>(ApiRequestDTO.builder()
                .message("Parameters not valid!")
                .httpStatus(HttpStatus.BAD_REQUEST)
                .fields(setFields(result.getFieldErrors()))
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailTakenException.class)
    public ResponseEntity<Object> handleException(EmailTakenException e){
        return new ResponseEntity<>(ApiRequestDTO.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .fields(Collections.singletonMap("e-mail","Email is taken."))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleException(BadCredentialsException e){
        return new ResponseEntity<>(ApiRequestDTO.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.FORBIDDEN)
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .fields(Collections.singletonMap("credentials","Email or password is incorrect."))
                .build(), HttpStatus.FORBIDDEN);
    }
    Map<String, String> setFields(List<FieldError> errors){
        Map<String, String> fields = new HashMap<>();
        errors.forEach(fieldError -> {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return fields;
    }
}
