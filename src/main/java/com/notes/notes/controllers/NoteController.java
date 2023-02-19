package com.notes.notes.controllers;

import com.notes.notes.modals.Note;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NoteController {
    @GetMapping("/note/{id}")
    public Note get(@RequestParam String id){
        return new Note();
    }

    @GetMapping("/notes/{authorId}")
    public List<Note> getNotesByAuthor(@RequestParam String authorId){
        List<Note> notes = new ArrayList<>();
        return notes;
    }

    @PostMapping("/note/edit")
    public Note editNote(@RequestBody Note updateNote, @RequestBody String id){
        return  new Note();
    }

    @PostMapping("/note")
    public Note createNote(@RequestBody Note note){
        return new Note();
    }
}
