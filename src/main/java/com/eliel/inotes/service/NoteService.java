package com.eliel.inotes.service;

import com.eliel.inotes.DTO.NoteDTO;
import com.eliel.inotes.model.Note;
import com.eliel.inotes.model.User;
import com.eliel.inotes.repo.NoteRepository;
import com.eliel.inotes.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class NoteService {
    private final NoteRepository repository;
    private final UserRepository userRepository;
    private final UserService userService;
    public Note createNote(NoteDTO dto){
        Note note = Note.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(userService.AuthUser())
                .build();
        return repository.save(note);
    }

    public List<Note> notesByAuthor(Long authorId){
        User author = userRepository.findById(authorId).orElseThrow();
        return repository.findByAuthor(author);
    }

    public Note findNoteById(Long id){
        return repository.findById(id).orElseThrow();
    }

    public Note updateNote(NoteDTO dto){
        Note note = repository.findById(dto.getId()).orElseThrow();
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        return repository.save(note);
    }

    public void deleteNote(Long id){
        repository.deleteById(id);
    }
}
