package com.example.school_bdd.service;


import com.example.school_bdd.entity.Note;
import com.example.school_bdd.repository.Note_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private Note_repository note_repository;

    // TODO : Ajouter les méthodes pour les requêtes HTTP
    //  GET, POST, PUT, DELETE

    public List<Note> getAllNotes() {
        return note_repository.findAll();
    }

    public Note getNoteById(Long id) {
        return note_repository.findById(id).orElse(null);
    }

    public void deleteNote(Long id) {
        note_repository.deleteById(id);
    }

    public Note updateNote(Long id, Note note) {
        note.setId(id);
        return note_repository.save(note);
    }

    public Note addNote(Note note) {
        return note_repository.save(note);
    }



}
