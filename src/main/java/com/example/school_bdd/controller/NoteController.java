package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Eleve;
import com.example.school_bdd.entity.Matiere;
import com.example.school_bdd.entity.Note;
import com.example.school_bdd.exception.ResourceNotFoundException;
import com.example.school_bdd.service.EleveService;
import com.example.school_bdd.service.MatiereService;
import com.example.school_bdd.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private MatiereService matiereService;

    @Autowired
    private EleveService eleveService;


    @GetMapping("/all")
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }


    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteNoteById(@PathVariable Long id) {
        noteService.deleteNote(id);
    }

    @GetMapping("/update/{id}")
    public Note updateNoteById(@PathVariable Long id, Note note) {
        return noteService.updateNote(id, note);
    }

    @PostMapping("/add/{idNote}/{idMatiere}")
    public Note addNoteById(@PathVariable Long idNote, @PathVariable Long idMatiere, @RequestBody Note note) {
        Matiere matiereExisting = matiereService.getMatiereById(idMatiere);
        Eleve eleveExisting = eleveService.getEleveById(idNote);


        if (matiereExisting == null) {
            throw new ResourceNotFoundException("Matiere not found");
        } else {

            note.setMatiere(matiereExisting);
            note.setEleve(eleveExisting);
            return noteService.addNote(note);
        }

    }


}
