package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Classe;
import com.example.school_bdd.entity.Etablissement;
import com.example.school_bdd.exception.ResourceNotFoundException;
import com.example.school_bdd.service.ClassesService;
import com.example.school_bdd.service.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {

    @Autowired
    private ClassesService classeService;

    @Autowired
    private EtablissementService etablissementService;

    @PostMapping("/add/{id}")
    public Classe addClasse(@RequestBody Classe classe, @PathVariable Long id) {
        Etablissement existEtablissement = etablissementService.getEtablissementById(id);
        if (existEtablissement == null) {
            throw new ResourceNotFoundException("Etablissement not found");
        } else {
            Etablissement etablissementCreate = new Etablissement();
            etablissementCreate.setId(id);
            classe.setEtablissement(etablissementCreate);
            return classeService.addClasse(classe);
        }

    }


    @GetMapping("/{id}")
    public Classe getClasse(@PathVariable Long id) {

        return classeService.getClasseById(id);
    }



    @GetMapping("/add/{id}")
    public ResponseEntity<List<Classe>> getClasseBySchools(@PathVariable Long id) {
        Etablissement existEtablissement = etablissementService.getEtablissementById(id);
        if (existEtablissement == null) {
            throw new ResourceNotFoundException(" Etablissement n'existe pas en base de donnée");
        } else {
            return ResponseEntity.ok(classeService.getClasseBySchool(id));
        }
    }



    @PutMapping("/{id}")
    public Classe updateClasse(@PathVariable Long id, @RequestBody Classe classe) {
        return classeService.updateClasse(id, classe);
    }

    @DeleteMapping("/{id}")
    public void deleteClasse(@PathVariable Long id) {

        classeService.deleteClasse(id);
    }

}
