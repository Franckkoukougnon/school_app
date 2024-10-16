package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Classe;
import com.example.school_bdd.entity.Matiere;
import com.example.school_bdd.exception.MatiereListNoFoundException;
import com.example.school_bdd.service.ClasseService;
import com.example.school_bdd.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/matiere")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;

    @Autowired
    private ClasseService classeService;


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

    //Methode pour afficher les listes des matiere par classe
    @GetMapping("/list/{idClasse}/matiere")
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
