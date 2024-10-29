package com.example.school_bdd.service;


import com.example.school_bdd.entity.Eleve;
import com.example.school_bdd.entity.Matiere;
import com.example.school_bdd.entity.Note;
import com.example.school_bdd.exception.ResourceNotFoundException;
import com.example.school_bdd.repository.Eleve_repo;
import com.example.school_bdd.repository.Matiere_repo;
import com.example.school_bdd.repository.Note_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private Note_repository noteRepository;

    @Autowired
    private Eleve_repo eleve_repo;

    @Autowired
    private Matiere_repo matiere_repo;


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

    public Note addNoteByEleveAndMatiere(Note note, Long idEleve, Long idMatiere) {
        // Vérification de l'existence de l'élève
        Eleve eleve = eleve_repo.findById(idEleve).orElseThrow(() ->
                new ResourceNotFoundException("Eleve avec id " + idEleve + " non trouvé"));

        // Vérification de l'existence de la matière
        Matiere matiere = matiere_repo.findById(idMatiere).orElseThrow(() ->
                new ResourceNotFoundException("Matière avec id " + idMatiere + " non trouvée"));

        // Affectation de l'élève et de la matière à la note
        note.setEleve(eleve);
        note.setMatiere(matiere);

        // Enregistrement de la note
        return noteRepository.save(note);
    }

    public double getMoyenneByEleveAndMatiere(Long idEleve, Long idMatiere) {
        // Récupération de la liste des notes de l'élève pour la matière donnée
        List<Note> notes = noteRepository.findByEleveIdAndMatiereId(idEleve, idMatiere);

        // Calcul de la moyenne
        double sum = 0;
        for (Note note : notes) {
            sum += note.getNote();
        }
        return sum / notes.size();
    }
}
