package com.notes.notes.expeptions;

public class EmailTaken extends RuntimeException{
    public EmailTaken(String message) {
        super(message);
    }
}
