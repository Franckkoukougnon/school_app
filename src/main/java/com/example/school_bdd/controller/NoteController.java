package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Note;
import com.example.school_bdd.exception.ResourceNotFoundException;
import com.example.school_bdd.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
@Tag(name = "Note", description = "API pour les opérations sur les notes")
public class NoteController {

    @Autowired
    private NoteService noteService;


    //Methode pour recuperer toutes les notes
    @GetMapping("/all")
    @Operation(summary = "Obtenir toutes les notes")
    public List<Note> getAllNotes(){
        return noteService.getAllNotes();
    }


    //Methode pour recuperer une note par son id
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une note par son ID")
    public Note getNoteById(@PathVariable Long id){
        return noteService.getNoteById(id);
    }


    //Methode pour ajouter une note
    @PostMapping("/add")
    @Operation(summary = "Ajouter une note")
    public Note addNote(@RequestBody Note note){
        return noteService.addNote(note);
    }

    //Methode pour ajouter une note pour un eleve et une matiere
    @PostMapping("/add/{idEleve}/{idMatiere}")
    @Operation(summary = "Ajouter une note pour un élève et une matière")
    public ResponseEntity<Note> addNoteByEleveAndMatiere(@RequestBody Note note, @PathVariable Long idEleve, @PathVariable Long idMatiere) {
        try {
            Note createdNote = noteService.addNoteByEleveAndMatiere(note, idEleve, idMatiere);
            return ResponseEntity.ok(createdNote);  // Retourne la note avec un status 200 OK
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 404 si l'eleve ou la matiere n'existe pas
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // 500 en cas d'erreur
        }
    }

    //Methode pour calculer la moyenne d'un eleve et pour une matiere
    @GetMapping("/moyenne/{idEleve}/{idMatiere}")
    @Operation(summary = "Calculer la moyenne d'un élève pour une matière")
    public double getMoyenneByEleveAndMatiere(@PathVariable Long idEleve, @PathVariable Long idMatiere){
        return noteService.getMoyenneByEleveAndMatiere(idEleve, idMatiere);
    }





    //Methode pour mettre à jour une note par son id
    @PutMapping("/update/{id}")
    @Operation(summary = "Mettre à jour une note par son ID")
    public Note updateNote(@PathVariable Long id,@RequestBody Note note){
        return noteService.updateNote(id, note);
    }

    //Methode pour supprimer une note par son id
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Supprimer une note par son ID")
    public void deleteNote(@PathVariable Long id){
        noteService.deleteNote(id);
    }
}
