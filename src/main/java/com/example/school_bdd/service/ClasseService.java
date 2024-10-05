package com.example.school_bdd.service;


import com.example.school_bdd.entity.Classe;
import com.example.school_bdd.repository.Classe_repo;
import com.example.school_bdd.repository.Etablissement_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasseService {

    @Autowired
    private Classe_repo classe_repo;

    @Autowired
    private Etablissement_repo etablissement_repo;

    public List<Classe> getAllClasses() {
        return classe_repo.findAll();
    }

    public Classe getClasseById(Long id) {
        return classe_repo.findById(id).orElse(null);
    }



    public Classe updateClasse(Long id, Classe classe) {
        Classe existingClasse = classe_repo.findById(id).orElse(null);
        if (existingClasse != null) {
            existingClasse.setName(classe.getName());
            existingClasse.setEtablissement(classe.getEtablissement());
            existingClasse.setMatieres(classe.getMatieres());
            existingClasse.setEleves(classe.getEleves());
            return classe_repo.save(existingClasse);
        } else {
            return null;
        }
    }

    public void deleteClasse(Long id) {
        Classe existingClasse = classe_repo.findById(id).orElse(null);
        if (existingClasse != null){
            classe_repo.delete(existingClasse);
        } else {
            throw new RuntimeException("Classe not found");
        }
    }

    public Classe addClasse(Classe classe) {
        return classe_repo.save(classe);
    }
}
