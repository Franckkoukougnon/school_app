package com.example.school_bdd.service;


import com.example.school_bdd.entity.Classe;
import com.example.school_bdd.entity.Etablissement;
import com.example.school_bdd.repository.Classe_repo;
import com.example.school_bdd.repository.Etablissement_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesService {

    @Autowired
    private Classe_repo classe_repo;

    public Classe addClasse(Classe classe) {
        return classe_repo.save(classe);
    }

    public Classe getClasseById(Long id) {
        return classe_repo.findById(id).orElseThrow(
                () -> new RuntimeException("Classe not found"));
    }

    public List<Classe> getAllClasses() {
        return classe_repo.findAll();
    }

    public Classe updateClasse(Long id, Classe classe) {
        classe.setId(id);
        return classe_repo.save(classe);
    }

    public void deleteClasse(Long id) {
        classe_repo.deleteById(id);
    }

    public List<Classe> getClasseBySchool(Long id) {
        return classe_repo.findByEtablissementId(id);
    }
}
