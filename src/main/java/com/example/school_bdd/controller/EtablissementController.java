package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Etablissement;
import com.example.school_bdd.service.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etablissement")
public class EtablissementController {

    @Autowired
    private EtablissementService etablissementService;


    @GetMapping("/all")
    public List<Etablissement> getAllEtablissements(){
        return etablissementService.getAllEtablissements();
    }

    @GetMapping("/{id}")
    public Etablissement getEtablissementById(Long id){
        return etablissementService.getEtablissementById(id);
    }

    @PostMapping("/add")
    public Etablissement addEtablissement(Etablissement etablissement){
        return etablissementService.addEtablissement(etablissement);
    }

    @PutMapping("/update/{id}")
    public Etablissement updateEtablissement(@PathVariable Long id, Etablissement etablissement){
        return etablissementService.updateEtablissement(id, etablissement);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEtablissement(@PathVariable Long id){
        etablissementService.deleteEtablissement(id);
    }


}
