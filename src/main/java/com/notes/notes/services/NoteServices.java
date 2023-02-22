package com.notes.notes.services;

import com.notes.notes.expeptions.NoteInvalid;
import com.notes.notes.expeptions.NoteNotFound;
import com.notes.notes.modals.Note;
import com.notes.notes.modals.User;
import com.notes.notes.repositories.NoteRepository;
import com.notes.notes.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Component
@Service
public class NoteServices {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteServices(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public Note createNote(String title, String content, UUID author) throws Exception{
        boolean authorValid = authorExists(author.toString());
        if(authorValid){
            Note note = new Note();
            note.setAuthor(author);
            note.setTitle(title);
            note.setContent(content);
            this.noteRepository.save(note);
            return note;
        }
        throw new NoteInvalid(NoteInvalid.message(false, false));
    }

    public Note editNote(String title, String content, String id){
        Note note = noteRepository.findById(id).orElseThrow(() -> {
            throw new NoteNotFound("This note is not found");
        });
            note.setTitle(title);
            note.setContent(content);
            noteRepository.save(note);
        return note;
    }

    public Note getNoteById(String id){
        return noteRepository.findById(id).orElseThrow(() -> {
            throw new NoteNotFound("This note is not found");
        });
    }

    public List<Note> getNotesByAuthor(String author){
        if(authorExists(author)){
            List<Note> notes = (List<Note>) noteRepository.findAll();
            return notes.stream()
                    .filter(note -> note.getAuthor().toString().equals(author))
                    .toList();
        }
        throw new NoteInvalid(NoteInvalid.message(true, false));
    }

    public void deleteNote(String id){
        if(noteRepository.existsById(id)){
            noteRepository.deleteById(id);
        }
        else{
            throw new NoteNotFound("This note doesn't exists!");
        }
    }

    public boolean authorExists(String author){
        boolean isValid = false;
        List<User> userList = (List<User>) userRepository.findAll();
        for (User user: userList) {
            if(user.getId().equals(author)){
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
