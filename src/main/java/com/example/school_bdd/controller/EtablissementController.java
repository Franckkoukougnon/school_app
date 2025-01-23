package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Etablissement;
import com.example.school_bdd.service.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/etablissement")
@RestController
public class EtablissementController {

    @Autowired
    private EtablissementService etablissementService;



    @GetMapping("/all")
    public List<Etablissement> getAllEtablissement() {
        return etablissementService.getAllEtablissement();
    }

    @GetMapping("/page/school")
    public ResponseEntity<Page<Etablissement>> getPageSchool(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(etablissementService.getPageSchool(page, size));
    }

    @PostMapping("/create")
    public ResponseEntity<Etablissement> createEtablissement(@RequestBody Etablissement etablissement) {
        return ResponseEntity.ok(etablissementService.createEtablissement(etablissement));
    }

    @PutMapping("/update/{idSchool}")
    public ResponseEntity<Etablissement> updateEtablissement(@RequestBody Etablissement etablissement, @PathVariable Long idSchool) {
        return ResponseEntity.ok(etablissementService.updateEtablissement(etablissement, idSchool));
    }


    @DeleteMapping("/delete/{idSchool}")
    public ResponseEntity<Etablissement> deleteEtablissement(@PathVariable Long idSchool) {
        return ResponseEntity.ok(etablissementService.deleteEtablissement(idSchool));
    }





}
