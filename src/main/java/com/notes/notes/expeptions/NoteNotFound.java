package com.notes.notes.expeptions;

public class NoteNotFound extends RuntimeException{
    public NoteNotFound(String message) {
        super(message);
    }
}
