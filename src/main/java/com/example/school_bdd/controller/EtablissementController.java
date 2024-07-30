package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Etablissement;
import com.example.school_bdd.service.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etablissements")
public class EtablissementController {




    @Autowired
    private EtablissementService etablissementService;

    @PostMapping("/add")
    public Etablissement addEtablissement(@RequestBody Etablissement etablissement) {
        return etablissementService.addEtablissement(etablissement);
    }

    @GetMapping("/{id}")
    public Etablissement getEtablissement(@PathVariable Long id) {
        return etablissementService.getEtablissementById(id);
    }

    @GetMapping("/all")
    public List<Etablissement> getAllEtablissements() {
        return etablissementService.getAllEtablissements();
    }

    @PutMapping("/{id}")
    public Etablissement updateEtablissement(@PathVariable Long id, @RequestBody Etablissement etablissement) {
        return etablissementService.updateEtablissement(id, etablissement);
    }

    @DeleteMapping("/{id}")
    public void deleteEtablissement(@PathVariable Long id) {
        etablissementService.deleteEtablissement(id);
    }

}
