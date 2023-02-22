package com.notes.notes.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.notes.notes.modals.Note;
import com.notes.notes.services.NoteServices;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
public class NoteController {
    private final NoteServices noteServices;

    public NoteController(NoteServices noteServices) {
        this.noteServices = noteServices;
    }

    @GetMapping("/note/{id}")
    public Note get(@PathVariable String id){
        return this.noteServices.getNoteById(id);
    }

    @GetMapping("/notes/{authorId}")
    public List<Note> getNotesByAuthor(@PathVariable String authorId){
        return this.noteServices.getNotesByAuthor(authorId);
    }

    @PostMapping("/note/edit")
    public Note editNote(@RequestBody ObjectNode json){
        return this.noteServices.editNote(json.get("title").textValue(), json.get("content").textValue(), json.get("id").textValue());
    }

    @PostMapping("/note")
    public Note createNote(@RequestBody @Valid Note note) throws Exception{
        return this.noteServices.createNote(note.getTitle(), note.getContent(), note.getAuthor());
    }

    @DeleteMapping("/note/{id}")
    public void deleteNote(@PathVariable String id){
        this.noteServices.deleteNote(id);
    }
}
