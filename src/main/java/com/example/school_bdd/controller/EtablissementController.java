package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Etablissement;
import com.example.school_bdd.service.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etablissement")
public class EtablissementController {

    @Autowired
    private EtablissementService etablissementService;


    //Methode pour recuperer tous les etablissements
    @GetMapping("/all")
    public List<Etablissement> getAllEtablissements(){
        return etablissementService.getAllEtablissements();
    }

    //Methode pour recuperer un etablissement par son id
    @GetMapping("/{id}")
    public Etablissement getEtablissementById(@PathVariable Long id){
        return etablissementService.getEtablissementById(id);
    }


    //Methode pour ajouter un etablissement
    @PostMapping("/add")
    public Etablissement addEtablissement(@RequestBody Etablissement etablissement){
        return etablissementService.addEtablissement(etablissement);
    }


    //Methode pour mettre à jour un etablissement par son id
    @PutMapping("/update/{id}")
    public Etablissement updateEtablissement(@PathVariable Long id,@RequestBody Etablissement etablissement){
        return etablissementService.updateEtablissement(id, etablissement);
    }


    //Methode pour supprimer un etablissement par son id
    @DeleteMapping("/delete/{id}")
    public void deleteEtablissement(@PathVariable Long id){
        etablissementService.deleteEtablissement(id);
    }


}
