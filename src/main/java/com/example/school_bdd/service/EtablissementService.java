package com.example.school_bdd.service;


import com.example.school_bdd.entity.Etablissement;
import com.example.school_bdd.exception.EtablissementNotFoundException;
import com.example.school_bdd.exception.ResourceNotFoundException;
import com.example.school_bdd.repository.Etablissement_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtablissementService {

    @Autowired
    private Etablissement_repo etablissement_repo;


    public Etablissement addEtablissement(Etablissement etablissement) {
        return etablissement_repo.save(etablissement);
    }

    public Etablissement getEtablissementById(Long id) {
        Optional<Etablissement> etablissementOptional = etablissement_repo.findById(id);
        return etablissementOptional.orElseThrow(() -> new EtablissementNotFoundException(id));
    }

    public List<Etablissement> getAllEtablissements() {
        return etablissement_repo.findAll();
    }

    public Etablissement updateEtablissement(Long id, Etablissement etablissement) {
        etablissement.setId(id);
        return etablissement_repo.save(etablissement);
    }

    public void deleteEtablissement(Long id) {
        etablissement_repo.deleteById(id);
    }
}
