package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Classe;
import com.example.school_bdd.entity.Eleve;
import com.example.school_bdd.entity.Matiere;
import com.example.school_bdd.exception.ResourceNotFoundException;
import com.example.school_bdd.service.EleveService;
import com.example.school_bdd.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matieres")
public class MatiereController {


    @Autowired
    private MatiereService matiereService;

    @Autowired
    private EleveService eleveService;

    @GetMapping("/all")
    public List<Matiere> getAllMatieres() {
        return matiereService.getAllMatieres();
    }

    @GetMapping("/{id}")
    public Matiere getMatiereById(@PathVariable Long id) {
        return matiereService.getMatiereById(id);
    }

    @PostMapping("/add/{id}")
    public Matiere addMatiere(@PathVariable Long id, @RequestBody Matiere matiere) {
        Eleve eleveExisting = eleveService.getEleveById(id);
        if (eleveExisting == null) {
            throw new ResourceNotFoundException("l'eleve n'existe pas en base de donnée");
        } else {

            Eleve eleveCreate = new Eleve();
            eleveCreate.setId(id);
            matiere.setEleves(eleveCreate);
            return matiereService.addMatiere(matiere);
        }
    }

    @PutMapping("/{id}")
    public Matiere updateMatiere(@PathVariable Long id, @RequestBody Matiere matiere) {
        matiere.setId(id);
        return matiereService.updateMatiere(id, matiere);
    }

    @DeleteMapping("/{id}")
    public void deleteMatiere(@PathVariable Long id) {
        matiereService.deleteMatiere(id);
    }





}
