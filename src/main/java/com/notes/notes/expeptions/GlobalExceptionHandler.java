package com.notes.notes.expeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailTaken.class)
    public ResponseEntity<ErrorTDO> handleEmailTaken(final EmailTaken et){
        return new ResponseEntity<ErrorTDO>(ErrorTDO.builder()
                .withTitle("Email taken")
                .withDetails("This email is taken for another user!")
                .withStatusCode(HttpStatus.CONFLICT.value())
                .withErrorType(EmailTaken.class.getSimpleName())
                .withErrorCode("409")
                .build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoteInvalid.class)
    public ResponseEntity<ErrorTDO> handleNoteInvalid(final NoteInvalid ni){
        return new ResponseEntity<>(ErrorTDO.builder()
                .withTitle("Invalid Note")
                .withDetails(ni.getMessage())
                .withStatusCode(HttpStatus.FORBIDDEN.value())
                .build(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoteNotFound.class)
    public ResponseEntity<ErrorTDO> handleNoteNotFound(final NoteNotFound nnf){
        return new ResponseEntity<>(ErrorTDO.builder()
                .withTitle("Note not found!")
                .withDetails(nnf.getMessage())
                .withStatusCode(HttpStatus.NOT_FOUND.value())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorTDO> handleUserNotFound(final UserNotFound unf){
        return new ResponseEntity<>(ErrorTDO.builder()
                .withTitle("User not found!")
                .withDetails(unf.getMessage())
                .withStatusCode(HttpStatus.NOT_FOUND.value())
                .build(), HttpStatus.NOT_FOUND);
    }

  /*  @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorTDO> handleExceptions(final Exception ex){
        return  new ResponseEntity<>(ErrorTDO.builder()
                .withDetails(ex.getMessage())
                .withStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withErrorType(ex.getClass().getSimpleName())
                .withErrorCode("C001")
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
