package com.example.school_bdd.controller;


import com.example.school_bdd.entity.Eleve;
import com.example.school_bdd.service.EleveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eleve")
public class EleveController {


    @Autowired
    private EleveService eleveService;

    @GetMapping("/all")
    public List<Eleve> getAllEleves(){
        return eleveService.getAllEleves();
    }

    @GetMapping("/{id}")
    public Eleve getEleveById(@PathVariable Long id){
        return eleveService.getEleveById(id);
    }

    @GetMapping("/moyenne/{id}")
    public Double calculerMoyenne(@PathVariable Long id){
        return eleveService.calculerMoyenne(id);
    }

    @PostMapping("/add")
    public Eleve addEleve(@RequestBody Eleve eleve){
        return eleveService.addEleve(eleve);
    }

    @PutMapping("/update/{id}")
    public Eleve updateEleve(@PathVariable Long id,@RequestBody Eleve eleve){
        return eleveService.updateEleve(id, eleve);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEleve(@PathVariable Long id){
        eleveService.deleteEleve(id);
    }
}
