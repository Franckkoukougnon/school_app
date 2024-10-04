package com.example.school_bdd.service;


import com.example.school_bdd.entity.Note;
import com.example.school_bdd.repository.Eleve_repo;
import com.example.school_bdd.repository.Note_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private Note_repository noteRepository;


    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(Long id, Note note) {
        Note existingNote = noteRepository.findById(id).orElse(null);
        if (existingNote != null) {
            existingNote.setNote(note.getNote());
            existingNote.setMatiere(note.getMatiere());
            existingNote.setEleve(note.getEleve());
            return noteRepository.save(existingNote);
        } else {
            return null;
        }
    }

    public void deleteNote(Long id) {
        Note existingNote = noteRepository.findById(id).orElse(null);
        if (existingNote != null){
            noteRepository.delete(existingNote);
        } else {
            throw new RuntimeException("Note not found");
        }
    }
}
