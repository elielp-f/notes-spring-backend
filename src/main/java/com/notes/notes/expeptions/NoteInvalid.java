package com.notes.notes.expeptions;

public class NoteInvalid extends RuntimeException{
    public NoteInvalid(String message) {
        super(message);
    }

    public static String message(boolean authorInvalid, boolean titleInvalid){
        String msg = "";
        if(authorInvalid){
            msg += "Author invalid!\n";
        }
        if(titleInvalid){
            msg += "Title invalid\n";
        }
        return msg;
    }
}
