package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Classe;
import com.example.school_bdd.entity.Matiere;
import com.example.school_bdd.exception.MatiereListNoFoundException;
import com.example.school_bdd.service.ClasseService;
import com.example.school_bdd.service.MatiereService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/matiere")
@Tag(name = "Matiere", description = "API pour les opérations sur les matières")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;

    @Autowired
    private ClasseService classeService;


    //Methode pour recuperer toutes les matieres
    @GetMapping("/all")
    @Operation(summary = "Obtenir toutes les matières")
    public List<Matiere> getAllMatieres(){
        return matiereService.getAllMatieres();
    }


    //Methode pour recuperer une matiere par son id
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une matière par son ID")
    public Matiere getMatiereById(@PathVariable Long id){
        return matiereService.getMatiereById(id);
    }


    //Methode pour ajouter une matiere
    @PostMapping("/add")
    @Operation(summary = "Ajouter une matière")
    public Matiere addMatiere(@RequestBody Matiere matiere){
        return matiereService.addMatiere(matiere);
    }


    //Methode pour mettre à jour une matiere par son id
    @PutMapping("/update/{id}")
    @Operation(summary = "Mettre à jour une matière par son ID")
    public Matiere updateMatiere(@PathVariable Long id,@RequestBody Matiere matiere){
        return matiereService.updateMatiere(id, matiere);
    }

    //Methode pour supprimer une matiere par son id
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Supprimer une matière par son ID")
    public void deleteMatiere(@PathVariable Long id){
        matiereService.deleteMatiere(id);
    }

    //Methode pour afficher les listes des matiere par classe
    @GetMapping("/list/{idClasse}/matiere")
    @Operation(summary = "Obtenir la liste des matières par classe")
    public List<Matiere> getListMatiereByClasse(@PathVariable Long idClasse){
        Classe classeExisting = classeService.getClasseById(idClasse);

        if (classeExisting != null){
            List<Matiere> matiereList = classeExisting.getMatieres();
            if (classeExisting == null || matiereList.isEmpty()){
                throw new MatiereListNoFoundException("La classe avec l'ID " + idClasse + " n'a pas de matières.");
            }
            return matiereList;
        }
        throw new MatiereListNoFoundException("Classe avec l'ID " + idClasse + " non trouvée.");
    }

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(MatiereListNoFoundException.class)
        public ResponseEntity<String> handleNoMatiereFoundException(MatiereListNoFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }


    }



}
