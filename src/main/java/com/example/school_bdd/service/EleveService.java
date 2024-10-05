package com.example.school_bdd.service;


import com.example.school_bdd.entity.Eleve;
import com.example.school_bdd.entity.Note;
import com.example.school_bdd.repository.Eleve_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EleveService {

    @Autowired
    private Eleve_repo eleve_repo;

    public List<Eleve> getAllEleves() {
        return eleve_repo.findAll();
    }

    public Eleve getEleveById(Long id) {
        return eleve_repo.findById(id).orElse(null);
    }

    public Eleve addEleve(Eleve eleve) {
        return eleve_repo.save(eleve);
    }

    public Eleve updateEleve(Long id, Eleve eleve) {
        Eleve existingEleve = eleve_repo.findById(id).orElse(null);
        if (existingEleve != null) {
            existingEleve.setNom(eleve.getNom());
            existingEleve.setPrenom(eleve.getPrenom());
            existingEleve.setClasse(eleve.getClasse());
            existingEleve.setNotes(eleve.getNotes());

            existingEleve.setMatricule(eleve.getMatricule());

            return eleve_repo.save(existingEleve);
        } else {
            return null;
        }
    }

    public void deleteEleve(Long id) {
        Eleve existingEleve = eleve_repo.findById(id).orElse(null);
        if (existingEleve != null){
            eleve_repo.delete(existingEleve);
        } else {
            throw new RuntimeException("Eleve not found");
        }
    }

    public Double calculerMoyenne(Long eleveId) {
        Eleve eleve = eleve_repo.findById(eleveId)
                .orElseThrow(() -> new RuntimeException("Élève non trouvé"));

        List<Note> notes = eleve.getNotes();

        // Calcul de la somme pondérée des notes et des coefficients
        double sommeNotesPondérées = 0.0;
        double sommeCoefficients = 0.0;

        for (Note note : notes) {
            double coefficient = note.getMatiere().getCoefficient();
            sommeNotesPondérées += note.getNote() * coefficient;
            sommeCoefficients += coefficient;
        }

        if (sommeCoefficients == 0) {
            return 0.0; // Évite la division par zéro si aucune matière n'a de coefficient
        }

        return sommeNotesPondérées * sommeCoefficients;
    }





}
