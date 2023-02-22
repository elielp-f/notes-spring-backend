package com.notes.notes.repositories;

import com.notes.notes.modals.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NoteRepository extends CrudRepository<Note, String> {
}
