package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Note;
import com.example.school_bdd.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;


    @GetMapping("/all")
    public List<Note> getAllNotes(){
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public Note getNoteById(Long id){
        return noteService.getNoteById(id);
    }

    @PostMapping("/add")
    public Note addNote(Note note){
        return noteService.addNote(note);
    }

    @PutMapping("/update/{id}")
    public Note updateNote(@PathVariable Long id, Note note){
        return noteService.updateNote(id, note);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNote(@PathVariable Long id){
        noteService.deleteNote(id);
    }
}
