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

    // TODO : Ajouter les méthodes pour les requêtes HTTP
    //  GET, POST, PUT, DELETE

    public List<Matiere> getAllMatieres() {
        return matiere_repo.findAll();
    }

    public Matiere getMatiereById(Long id) {
        return matiere_repo.findById(id).orElse(null);
    }



    public void deleteMatiere(Long id) {
        matiere_repo.deleteById(id);
    }

    public Matiere updateMatiere(Long id, Matiere matiere) {
        matiere.setId(id);
        return matiere_repo.save(matiere);
    }


    public Matiere addMatiere(Matiere matiere) {
        return matiere_repo.save(matiere);
    }
}
