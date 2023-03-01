package com.eliel.inotes.controller;

import com.eliel.inotes.DTO.NoteDTO;
import com.eliel.inotes.model.Note;
import com.eliel.inotes.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService service;
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody @Valid NoteDTO dto){
        return ResponseEntity.ok(service.createNote(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> findNoteById(@PathVariable Long id){
        return ResponseEntity.ok(service.findNoteById(id));
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<Note>> findNotesByAuthor(@PathVariable Long id){
        return ResponseEntity.ok(service.notesByAuthor(id));
    }

    @PutMapping
    public ResponseEntity<Note> updateNote(@RequestBody @Valid NoteDTO dto){
        return ResponseEntity.ok(service.updateNote(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id){
        service.deleteNote(id);
        return ResponseEntity.ok().build();
    }

}
