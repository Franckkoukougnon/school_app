package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Etablissement;
import com.example.school_bdd.service.EtablissementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etablissement")
@Tag(name = "Etablissement", description = "API pour les opérations sur les établissements")
public class EtablissementController {

    @Autowired
    private EtablissementService etablissementService;


    //Methode pour recuperer tous les etablissements
    @GetMapping("/all")
    @Operation(summary = "Obtenir tous les établissements")
    public List<Etablissement> getAllEtablissements(){
        return etablissementService.getAllEtablissements();
    }

    //Methode pour recuperer un etablissement par son id
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un établissement par son ID")
    public Etablissement getEtablissementById(@PathVariable Long id){
        return etablissementService.getEtablissementById(id);
    }


    //Methode pour ajouter un etablissement
    @PostMapping("/add")
    @Operation(summary = "Ajouter un établissement")
    public Etablissement addEtablissement(@RequestBody Etablissement etablissement){
        return etablissementService.addEtablissement(etablissement);
    }


    //Methode pour mettre à jour un etablissement par son id
    @PutMapping("/update/{id}")
    @Operation(summary = "Mettre à jour un établissement par son ID")
    public Etablissement updateEtablissement(@PathVariable Long id,@RequestBody Etablissement etablissement){
        return etablissementService.updateEtablissement(id, etablissement);
    }


    //Methode pour supprimer un etablissement par son id
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Supprimer un établissement par son ID")
    public void deleteEtablissement(@PathVariable Long id){
        etablissementService.deleteEtablissement(id);
    }


}
