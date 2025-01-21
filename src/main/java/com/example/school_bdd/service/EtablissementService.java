package com.example.school_bdd.service;


import com.example.school_bdd.entity.Etablissement;
import com.example.school_bdd.repository.Etablissement_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtablissementService {

    @Autowired
    private Etablissement_repo etablissment_repo;

    public List<Etablissement> getAllEtablissements() {
        return etablissment_repo.findAll();
    }

    public Page<Etablissement>PageOfEtablissment(Pageable pageable){
        return etablissment_repo.findAll(pageable);
    }

    public Etablissement getEtablissementById(Long id) {
        return etablissment_repo.findById(id).orElseThrow(
                () -> new RuntimeException("Etablissment with id " + id + " not found")
        );
    }

    public Etablissement addEtablissement(Etablissement etablissement) {
        return etablissment_repo.save(etablissement);
    }

    public Etablissement updateEtablissement(Long id, Etablissement etablissement) {
        Etablissement existingEtablissement = etablissment_repo.findById(id).orElseThrow(()-> new RuntimeException("Etablissement with id " + id + " not found"));
        if (existingEtablissement != null) {
            existingEtablissement.setNom(etablissement.getNom());
            existingEtablissement.setEmail(etablissement.getEmail());
            existingEtablissement.setClasses(etablissement.getClasses());
            return etablissment_repo.save(existingEtablissement);
        } else {
            throw new RuntimeException("Etablissement not found");
        }
    }

    public void deleteEtablissement(Long id) {
        Etablissement existingEtablissement = etablissment_repo.findById(id).orElse(null);
        if (existingEtablissement != null){
            etablissment_repo.delete(existingEtablissement);
        } else {
            throw new RuntimeException("Etablissement not found");
        }
    }

    public Page<Etablissement> finByNomStartingWith(String nom, Pageable pageable) {
        return etablissment_repo.findByNomStartingWith(nom, pageable);
    }
}
