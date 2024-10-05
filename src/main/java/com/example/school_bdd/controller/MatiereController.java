package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Matiere;
import com.example.school_bdd.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matiere")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;


    //Methode pour recuperer toutes les matieres
    @GetMapping("/all")
    public List<Matiere> getAllMatieres(){
        return matiereService.getAllMatieres();
    }


    //Methode pour recuperer une matiere par son id
    @GetMapping("/{id}")
    public Matiere getMatiereById(@PathVariable Long id){
        return matiereService.getMatiereById(id);
    }


    //Methode pour ajouter une matiere
    @PostMapping("/add")
    public Matiere addMatiere(@RequestBody Matiere matiere){
        return matiereService.addMatiere(matiere);
    }


    //Methode pour mettre à jour une matiere par son id
    @PutMapping("/update/{id}")
    public Matiere updateMatiere(@PathVariable Long id,@RequestBody Matiere matiere){
        return matiereService.updateMatiere(id, matiere);
    }

    //Methode pour supprimer une matiere par son id
    @DeleteMapping("/delete/{id}")
    public void deleteMatiere(@PathVariable Long id){
        matiereService.deleteMatiere(id);
    }

}
