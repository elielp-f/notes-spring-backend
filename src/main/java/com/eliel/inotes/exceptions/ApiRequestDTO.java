package com.eliel.inotes.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Map;

@Builder @Data
public class ApiRequestDTO {
    String message;
    HttpStatus httpStatus;
    ZonedDateTime timestamp;
    Map<String, String> fields;
}
