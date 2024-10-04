package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Matiere;
import com.example.school_bdd.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matiere")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;

    @GetMapping("/all")
    public List<Matiere> getAllMatieres(){
        return matiereService.getAllMatieres();
    }

    @GetMapping("/{id}")
    public Matiere getMatiereById(Long id){
        return matiereService.getMatiereById(id);
    }

    @PostMapping("/add")
    public Matiere addMatiere(Matiere matiere){
        return matiereService.addMatiere(matiere);
    }

    @PutMapping("/update/{id}")
    public Matiere updateMatiere(@PathVariable Long id, Matiere matiere){
        return matiereService.updateMatiere(id, matiere);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMatiere(@PathVariable Long id){
        matiereService.deleteMatiere(id);
    }

}
