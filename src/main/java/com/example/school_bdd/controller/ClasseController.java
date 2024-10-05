package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Classe;
import com.example.school_bdd.entity.Etablissement;
import com.example.school_bdd.service.ClasseService;
import com.example.school_bdd.service.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classe")
public class ClasseController {

    @Autowired
    private ClasseService classeService;

    @Autowired
    private EtablissementService etablissementService;



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



    //Methode pour recuperer les classes d'un etablissement par son id
    @GetMapping("/etablissement/{idEtablissement}/classe/{idClasse}")
    public ResponseEntity<Classe> getClasseByEtablissement(@PathVariable long idEtablissement, @PathVariable long idClasse) {

        // Vérification de l'existence de l'établissement
        Etablissement etablissement = checkEtablissementExists(idEtablissement);
        if (etablissement == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Récupérer la classe
        Classe classe = checkClasseExists(idClasse);

        if (classe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(classe);
    }

    // Méthode pour vérifier l'existence d'un établissement
    private Etablissement checkEtablissementExists(Long idEtablissement) {
        return etablissementService.getEtablissementById(idEtablissement);
    }


    // Méthode pour vérifier l'existence d'une classe
    private Classe checkClasseExists(Long idClasse) {
        return classeService.getClasseById(idClasse);
    }



    //Methode pour ajouter une classe
    @PostMapping("/add/{idEtablissement}")
    public ResponseEntity<Classe> addClasse(@PathVariable long idEtablissement, @RequestBody Classe classe) {
        // Vérification de l'existence de l'établissement
        Etablissement etablissement = checkEtablissementExists(idEtablissement);

        if (etablissement == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Associer l'établissement à la classe
        classe.setEtablissement(etablissement);

        // Enregistrer la classe
        Classe savedClasse = classeService.addClasse(classe);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedClasse);
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
