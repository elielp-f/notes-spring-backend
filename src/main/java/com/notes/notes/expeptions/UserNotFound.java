package com.notes.notes.expeptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String message) {
        super(message);
    }
}
