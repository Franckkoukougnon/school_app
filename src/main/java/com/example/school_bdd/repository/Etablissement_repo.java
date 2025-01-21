package com.example.school_bdd.repository;

import com.example.school_bdd.entity.Etablissement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Etablissement_repo extends JpaRepository<Etablissement, Long> {
    Page<Etablissement> findByNomStartingWith(String nom, Pageable pageable);
}
