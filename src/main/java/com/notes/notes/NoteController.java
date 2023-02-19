package com.notes.notes;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {
    public Note get(){
        return new Note();
    }
}
