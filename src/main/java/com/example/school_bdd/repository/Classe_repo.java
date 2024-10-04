package com.example.school_bdd.repository;

import com.example.school_bdd.entity.Classe;
import com.example.school_bdd.entity.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Classe_repo extends JpaRepository<Classe, Long> {


    List<Classe> findByEtablissementId(Long id);

    Optional<Classe> findByNameAndEtablissement(String name, Etablissement etablissement);
}
