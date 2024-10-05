package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Classe;
import com.example.school_bdd.service.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classe")
public class ClasseController {

    @Autowired
    private ClasseService classeService;


    @GetMapping("/all")
    public List<Classe> getAllClasses(){
        return classeService.getAllClasses();
    }

    @GetMapping("/{id}")
    public Classe getClasseById(@PathVariable Long id){
        return classeService.getClasseById(id);
    }

    @PostMapping("/add")
    public Classe addClasse(@RequestBody Classe classe){
        return classeService.addClasse(classe);
    }

    @PutMapping("/update/{id}")
    public Classe updateClasse(@PathVariable Long id,@RequestBody Classe classe){
        return classeService.updateClasse(id, classe);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClasse(@PathVariable Long id){
        classeService.deleteClasse(id);
    }
}
