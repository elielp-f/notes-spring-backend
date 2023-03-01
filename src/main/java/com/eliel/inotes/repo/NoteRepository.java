package com.eliel.inotes.repo;

import com.eliel.inotes.model.Note;
import com.eliel.inotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByAuthor(User author);
}
