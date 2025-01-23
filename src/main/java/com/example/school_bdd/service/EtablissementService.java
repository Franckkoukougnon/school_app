package com.example.school_bdd.service;


import com.example.school_bdd.entity.Etablissement;
import com.example.school_bdd.repository.Classe_repo;
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

    @Autowired
    private Classe_repo classe_repo;


    public Etablissement getEtablissementById(Long idSchool) {
        return etablissment_repo.findById(idSchool).orElse(null);
    }

    public List<Etablissement> getAllEtablissement() {
        return etablissment_repo.findAll();
    }

    public Page<Etablissement> getPageSchool(int page, int size) {
        return etablissment_repo.findAll(Pageable.ofSize(size).withPage(page));
    }

    public Etablissement createEtablissement(Etablissement etablissement) {
        return etablissment_repo.save(etablissement);
    }

    public Etablissement updateEtablissement(Etablissement etablissement, Long idSchool) {
        Etablissement etablissementToUpdate = etablissment_repo.findById(idSchool).orElse(null);
        if (etablissementToUpdate == null) {
            return null;
        }
        etablissementToUpdate.setNom(etablissement.getNom());
        etablissementToUpdate.setEmail(etablissement.getEmail());
        etablissementToUpdate.setClasses(etablissement.getClasses());

        return etablissment_repo.save(etablissementToUpdate);

    }

    public Etablissement deleteEtablissement (Long idSchool) {
        Etablissement etablissementToDelete = etablissment_repo.findById(idSchool).orElse(null);
        if (etablissementToDelete == null) {
            return null;
        }
        etablissment_repo.delete(etablissementToDelete);
        return etablissementToDelete;
    }
}
