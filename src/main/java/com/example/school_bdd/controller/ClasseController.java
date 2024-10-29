package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Classe;
import com.example.school_bdd.entity.Etablissement;
import com.example.school_bdd.service.ClasseService;
import com.example.school_bdd.service.EtablissementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classe")
@Tag(name = "Classe", description = "API pour les opérations sur les classes")
public class ClasseController {

    @Autowired
    private ClasseService classeService;

    @Autowired
    private EtablissementService etablissementService;



    //Methode pour recuperer toutes les classes
    @GetMapping("/all")
    @Operation(summary = "Obtenir toutes les classes")
    public List<Classe> getAllClasses(){

        return classeService.getAllClasses();
    }

    //Methode pour recuperer une classe par son id
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une classe par son ID")
    public Classe getClasseById(@PathVariable Long id){
        return classeService.getClasseById(id);
    }



    //Methode pour recuperer les classes d'un etablissement par son id
    @GetMapping("/etablissement/{idEtablissement}/classe/{idClasse}")
    @Operation(summary = "Obtenir une classe par son ID et l'ID de l'établissement")
    public Classe getClasseByEtablissement(@PathVariable Long idEtablissement, @PathVariable Long idClasse) {
        // Récupérer l'établissement par son ID
        Etablissement etablissement = checkEtablissementExists(idEtablissement);
        if (etablissement == null) {
            return null; // Établissement non trouvé
        }

        // Récupérer la classe par son ID
        Classe classe = checkClasseExists(idClasse);
        if (classe == null) {
            return null; // Classe non trouvée
        }

        // Vérifier que la classe appartient bien à l'établissement
        if (!classe.getEtablissement().getId().equals(etablissement.getId())) {
            return null; // La classe ne correspond pas à l'établissement
        }

        // Si tout est correct, retourner la classe
        return classe;
    }

    @GetMapping("/etablissement/{idEtablissement}/all")
    @Operation(summary = "Obtenir toutes les classes d'un établissement par son ID")
    public List<Classe> getClassesByEtablissement(@PathVariable Long idEtablissement) {
        // Récupérer l'établissement par son ID
        Etablissement etablissement = checkEtablissementExists(idEtablissement);
        if (etablissement == null) {
            return null; // Établissement non trouvé
        }

        // Récupérer les classes de l'établissement
        return classeService.getClassesByEtablissement(etablissement);
    }

    @DeleteMapping("/delete/etablissement/{idEtablissement}/classe/{idClasse}")
    @Operation(summary = "Supprimer une classe par son ID et l'ID de l'établissement")
    public void deleteClasseByEtablissement(@PathVariable Long idEtablissement, @PathVariable Long idClasse) {
        // Récupérer l'établissement par son ID
        Etablissement etablissement = checkEtablissementExists(idEtablissement);
        if (etablissement == null) {
            return; // Établissement non trouvé
        }

        // Récupérer la classe par son ID
        Classe classe = checkClasseExists(idClasse);
        if (classe == null) {
            return; // Classe non trouvée
        }

        // Vérifier que la classe appartient bien à l'établissement
        if (!classe.getEtablissement().getId().equals(etablissement.getId())) {
            return; // La classe ne correspond pas à l'établissement
        }

        // Supprimer la classe
        classeService.deleteClasse(idClasse);
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
    @Operation(summary = "Ajouter une classe pour un établissement par son ID")
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
    @Operation(summary = "Mettre à jour une classe par son ID")
    public Classe updateClasse(@PathVariable Long id,@RequestBody Classe classe){
        return classeService.updateClasse(id, classe);
    }

    //Methode pour supprimer une classe par son id
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Supprimer une classe par son ID")
    public void deleteClasse(@PathVariable Long id){
        classeService.deleteClasse(id);
    }
}
