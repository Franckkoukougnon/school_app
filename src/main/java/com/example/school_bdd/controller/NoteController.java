package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Note;
import com.example.school_bdd.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    @Autowired
    private NoteService noteService;


    //Methode pour recuperer toutes les notes
    @GetMapping("/all")
    public List<Note> getAllNotes(){
        return noteService.getAllNotes();
    }


    //Methode pour recuperer une note par son id
    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id){
        return noteService.getNoteById(id);
    }


    //Methode pour ajouter une note
    @PostMapping("/add")
    public Note addNote(@RequestBody Note note){
        return noteService.addNote(note);
    }


    //Methode pour mettre à jour une note par son id
    @PutMapping("/update/{id}")
    public Note updateNote(@PathVariable Long id,@RequestBody Note note){
        return noteService.updateNote(id, note);
    }

    //Methode pour supprimer une note par son id
    @DeleteMapping("/delete/{id}")
    public void deleteNote(@PathVariable Long id){
        noteService.deleteNote(id);
    }
}
