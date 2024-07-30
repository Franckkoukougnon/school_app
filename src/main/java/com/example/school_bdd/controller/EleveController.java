package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Classe;
import com.example.school_bdd.entity.Eleve;
import com.example.school_bdd.exception.ResourceNotFoundException;
import com.example.school_bdd.service.ClassesService;
import com.example.school_bdd.service.EleveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eleves")
public class EleveController {


    @Autowired
    private EleveService eleveService;

    @Autowired
    private ClassesService classeService;

    @PostMapping("/add/{id}")
    public Eleve addEleve(@RequestBody Eleve eleve, @PathVariable Long id) {
        Classe existClasse = classeService.getClasseById(id);

        if (existClasse == null) {
            throw new ResourceNotFoundException("Classe not found");
        } else {
            Classe classeCreate = new Classe();
            classeCreate.setId(id);
            eleve.setClasse(classeCreate);
            return eleveService.addEleve(eleve);
        }
    }

    @GetMapping("/{id}")
    public Eleve getEleve(@PathVariable Long id) {
        return eleveService.getEleveById(id);
    }

    @GetMapping
    public List<Eleve> getAllEleves() {
        return eleveService.getAllEleves();
    }

    @PutMapping("/{id}")
    public Eleve updateEleve(@PathVariable Long id, @RequestBody Eleve eleve) {
        return eleveService.updateEleve(id, eleve);
    }

    @DeleteMapping("/{id}")
    public void deleteEleve(@PathVariable Long id) {
        eleveService.deleteEleve(id);
    }

}
