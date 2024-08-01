package com.example.school_bdd.service;


import com.example.school_bdd.entity.Eleve;
import com.example.school_bdd.entity.Matiere;
import com.example.school_bdd.entity.Note;
import com.example.school_bdd.repository.Eleve_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EleveService {

    @Autowired
    private Eleve_repo eleve_repo;


    public Eleve addEleve(Eleve eleve) {
        return eleve_repo.save(eleve);
    }

    public Eleve getEleveById(Long id) {
        return eleve_repo.findById(id).orElseThrow(
                () -> new RuntimeException("Eleve not found"));
    }

    public List<Eleve> getAllEleves() {
        return eleve_repo.findAll();
    }

    public Eleve updateEleve(Long id, Eleve eleve) {
        eleve.setId(id);
        return eleve_repo.save(eleve);
    }

    public void deleteEleve(Long id) {
        eleve_repo.deleteById(id);
    }

    public Double calculerMoyenne(Long eleveId) {
        Eleve eleve = getEleveById(eleveId);
        List<Note> notes = eleve.getNotes();
        if (notes == null || notes.isEmpty()) {
            return 0.0;
        }
        Double somme = 0.0;
        Double totalCoefficients = 0.0;
        for (Note note : notes) {
            Matiere matiere = note.getMatiere(); // corrected to getMatiere() instead of getMatieres()
            Double coefficient = matiere.getCoefficient();
            somme += note.getNote() * coefficient; // corrected to getValeur() instead of getNote()
            totalCoefficients += coefficient;
        }
        return totalCoefficients == 0 ? 0.0 : somme / totalCoefficients;
    }



}
