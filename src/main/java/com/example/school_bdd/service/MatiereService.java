package com.example.school_bdd.service;


import com.example.school_bdd.entity.Matiere;
import com.example.school_bdd.repository.Matiere_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatiereService {

    @Autowired
    private Matiere_repo matiere_repo;

    public List<Matiere> getAllMatieres() {
        return matiere_repo.findAll();
    }

    public Matiere getMatiereById(Long id) {
        return matiere_repo.findById(id).orElse(null);
    }

    public Matiere addMatiere(Matiere matiere) {
        return matiere_repo.save(matiere);
    }

    public Matiere updateMatiere(Long id, Matiere matiere) {
        Matiere existingMatiere = matiere_repo.findById(id).orElse(null);
        if (existingMatiere != null) {
            existingMatiere.setName(matiere.getName());
            existingMatiere.setCoefficient(matiere.getCoefficient());
            existingMatiere.setClasse(matiere.getClasse());
            existingMatiere.setNotes(matiere.getNotes());
            return matiere_repo.save(existingMatiere);
        } else {
            return null;
        }
    }

    public void deleteMatiere(Long id) {
        Matiere existingMatiere = matiere_repo.findById(id).orElse(null);
        if (existingMatiere != null){
            matiere_repo.delete(existingMatiere);
        } else {
            throw new RuntimeException("Matiere not found");
        }
    }
}
