package com.example.school_bdd.repository;

import com.example.school_bdd.entity.Classe;
import com.example.school_bdd.entity.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Eleve_repo extends JpaRepository<Eleve, Long> {
    List<Eleve> findByClasse(Classe classe);

    Eleve findByMatricule(int matricule);
}
