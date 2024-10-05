package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Classe;
import com.example.school_bdd.service.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classe")
public class ClasseController {

    @Autowired
    private ClasseService classeService;



    //Methode pour recuperer toutes les classes
    @GetMapping("/all")
    public List<Classe> getAllClasses(){
        return classeService.getAllClasses();
    }

    //Methode pour recuperer une classe par son id
    @GetMapping("/{id}")
    public Classe getClasseById(@PathVariable Long id){
        return classeService.getClasseById(id);
    }


    //Methode pour ajouter une classe
    @PostMapping("/add")
    public Classe addClasse(@RequestBody Classe classe){
        return classeService.addClasse(classe);
    }


    //Methode pour mettre à jour une classe par son id
    @PutMapping("/update/{id}")
    public Classe updateClasse(@PathVariable Long id,@RequestBody Classe classe){
        return classeService.updateClasse(id, classe);
    }

    //Methode pour supprimer une classe par son id
    @DeleteMapping("/delete/{id}")
    public void deleteClasse(@PathVariable Long id){
        classeService.deleteClasse(id);
    }
}
