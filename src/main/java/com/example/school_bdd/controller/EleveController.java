package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Classe;
import com.example.school_bdd.entity.Eleve;
import com.example.school_bdd.service.ClasseService;
import com.example.school_bdd.service.EleveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eleve")
public class EleveController {


    @Autowired
    private EleveService eleveService;

    @Autowired
    private ClasseService classeService;


    //Methode pour recuperer tous les eleves
    @GetMapping("/all")
    public List<Eleve> getAllEleves(){
        return eleveService.getAllEleves();
    }

    
    //Methode pour recuperer un eleve par son id
    @GetMapping("/{id}")
    public Eleve getEleveById(@PathVariable Long id){
        return eleveService.getEleveById(id);
    }

    @GetMapping("/classe/{idClasse}/eleve/{idEleve}")
    public Eleve getEleveByClasse(@PathVariable Long idClasse, @PathVariable Long idEleve) {
        Classe classe = checkClasseExists(idClasse);
        if (classe == null) {
            return null;
        }
        Eleve eleve = checkEleveExists(idEleve);
        if (eleve == null) {
            return null;
        }
        return eleve;
    }

    
    //Methode pour calculer la moyenne d'un eleve par son id
    @GetMapping("/moyenne/{id}")
    public Double calculerMoyenne(@PathVariable Long id){
        return eleveService.calculerMoyenne(id);
    }

    
    //Methode pour ajouter un eleve
    @PostMapping("/add/{idClasse}")
    public Eleve addEleve(@RequestBody Eleve eleve, @PathVariable Long idClasse){
        Classe classe = checkClasseExists(idClasse);
        if (classe == null) {
            return null;
        }
        eleve.setClasse(classe);
        return eleveService.addEleve(eleve);
    }


    public Eleve checkEleveExists(Long id) {
        Eleve eleve = eleveService.getEleveById(id);
        if (eleve == null) {
            return null;
        }
        return eleve;
    }

    public Classe checkClasseExists(Long idClasse) {
        Classe classeExisting = classeService.getClasseById(idClasse);
        if (classeExisting == null) {
            return null;
        }
        return classeExisting;
    }

    
    //Methode pour mettre à jour un eleve par son id
    @PutMapping("/update/{id}")
    public Eleve updateEleve(@PathVariable Long id,@RequestBody Eleve eleve){
        return eleveService.updateEleve(id, eleve);
    }

    
    //Methode pour supprimer un eleve par son id
    @DeleteMapping("/delete/{id}")
    public void deleteEleve(@PathVariable Long id){
        eleveService.deleteEleve(id);
    }
}
